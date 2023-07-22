package cc.bitky.jetbrains.plugin.universalgenerate.util.builder;

import cc.bitky.jetbrains.plugin.universalgenerate.pojo.PsiClassWrapper;
import cc.bitky.jetbrains.plugin.universalgenerate.pojo.PsiFieldWrapper;
import cc.bitky.jetbrains.plugin.universalgenerate.pojo.PsiMethodWrapper;
import cc.bitky.jetbrains.plugin.universalgenerate.pojo.WriteContext;
import cc.bitky.jetbrains.plugin.universalgenerate.util.BitkylinPsiParseUtils;
import cc.bitky.jetbrains.plugin.universalgenerate.util.DecisionUtils;
import com.google.common.base.Preconditions;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author bitkylin
 */
public final class WriteContextBuilder {

    private final AnActionEvent anActionEvent;

    private WriteContextBuilder(AnActionEvent anActionEvent) {
        this.anActionEvent = anActionEvent;
    }

    public static WriteContext create(AnActionEvent anActionEvent) {
        return new WriteContextBuilder(anActionEvent).innerCreate();
    }

    public WriteContext innerCreate() {
        Project project = anActionEvent.getProject();
        Preconditions.checkNotNull(project);

        Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        Preconditions.checkNotNull(editor);

        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        Preconditions.checkNotNull(psiFile);

        PsiClass psiClass = PsiTreeUtil.findChildOfAnyType(psiFile, PsiClass.class);
        Preconditions.checkNotNull(psiClass);

        // 通过光标偏移量获取当前psi元素
        PsiElement currentElement = psiFile.findElementAt(editor.getCaretModel().getOffset());

        WriteContext writeContext = new WriteContext();
        WriteContext.PsiFileContext psiFileContext = new WriteContext.PsiFileContext();
        writeContext.setPsiFileContext(psiFileContext);

        psiFileContext.setProject(project);
        psiFileContext.setPsiFile(psiFile);
        psiFileContext.setPsiClass(psiClass);
        psiFileContext.setElementFactory(JavaPsiFacade.getElementFactory(project));

        WriteContext.SelectWrapper selectWrapper = new WriteContext.SelectWrapper();
        selectWrapper.setCurrentElement(currentElement);
        writeContext.setSelectWrapper(selectWrapper);

        assembleUniversalClassInfo(0, psiClass, writeContext);

        return writeContext;
    }

    private void assembleUniversalClassInfo(int depth, PsiClass psiClass, WriteContext writeContext) {

        WriteContext.SelectWrapper selectWrapper = writeContext.getSelectWrapper();
        PsiElement currentElement = selectWrapper.getCurrentElement();

        Preconditions.checkArgument(depth < 5);


        PsiClassWrapper psiClassWrapper = createPsiClassWrapper(psiClass, writeContext.getPsiFileContext());
        writeContext.addClassWrapper(psiClassWrapper);

        if (depth == 0) {
            writeContext.setFilePsiClassWrapper(psiClassWrapper);
        }

        if (currentElement.getTextOffset() == psiClass.getTextOffset()) {
            selectWrapper.setClz(psiClass);
            selectWrapper.setSelectedPsiClassWrapper(psiClassWrapper);
            selectWrapper.setSelected(true);
        }

        for (PsiFieldWrapper field : psiClassWrapper.getFieldList()) {
            PsiField psiField = field.getPsiField();
            if (currentElement.getTextOffset() == psiField.getTextOffset()) {
                selectWrapper.setField(field);
                selectWrapper.setSelectedPsiClassWrapper(psiClassWrapper);
                selectWrapper.setSelected(true);
                break;
            }
        }
        for (PsiMethodWrapper method : psiClassWrapper.getMethodList()) {
            PsiMethod psiMethod = method.getPsiMethod();
            if (currentElement.getTextOffset() == psiMethod.getTextOffset()) {
                selectWrapper.setMethod(method);
                selectWrapper.setSelectedPsiClassWrapper(psiClassWrapper);
                selectWrapper.setSelected(true);
                break;
            }
        }

        if (psiClass.isEnum()) {
            return;
        }

        if (depth >= 1) {
            return;
        }

        psiClassWrapper.setInnerClassList(Arrays.stream(psiClass.getAllInnerClasses())
                .map(psiClassItem -> createPsiClassWrapper(psiClassItem, writeContext.getPsiFileContext()))
                .toList());

        if (CollectionUtils.isEmpty(psiClassWrapper.getInnerClassList())) {
            return;
        }

        for (PsiClassWrapper innerClass : psiClassWrapper.getInnerClassList()) {
            assembleUniversalClassInfo(depth + 1, innerClass.getPsiClass(), writeContext);
        }

    }

    @NotNull
    private PsiClassWrapper createPsiClassWrapper(PsiClass psiClass, WriteContext.PsiFileContext psiFileContext) {
        PsiClassWrapper psiClassWrapper = new PsiClassWrapper();
        psiClassWrapper.setPsiClass(psiClass);
        psiClassWrapper.setFieldList(Stream.of(psiClass.getFields()).map(BitkylinPsiParseUtils::parsePsiField).toList());
        psiClassWrapper.setMethodList(Stream.of(psiClass.getMethods()).map(BitkylinPsiParseUtils::parsePsiMethod).toList());

        DecisionUtils.assembleRoleAndLocation(psiClassWrapper, psiFileContext);
        return psiClassWrapper;
    }

}