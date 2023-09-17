package cc.bitky.jetbrains.plugin.universalgenerate.action.action;

import cc.bitky.jetbrains.plugin.universalgenerate.action.action.base.AbstractUniversalGenerateAction;
import cc.bitky.jetbrains.plugin.universalgenerate.config.settings.state.GlobalSettingsStateHelper;
import cc.bitky.jetbrains.plugin.universalgenerate.constants.ActionEnum;
import cc.bitky.jetbrains.plugin.universalgenerate.factory.CommandCommandTypeProcessorFactory;
import cc.bitky.jetbrains.plugin.universalgenerate.pojo.WriteCommand;
import cc.bitky.jetbrains.plugin.universalgenerate.pojo.WriteContext;
import cc.bitky.jetbrains.plugin.universalgenerate.util.WriteCommandActionUtils;
import cc.bitky.jetbrains.plugin.universalgenerate.util.builder.WriteContextActionBuilder;
import com.intellij.openapi.actionSystem.AnActionEvent;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author bitkylin
 */
@Slf4j
public class DeleteElementAnnotationSwaggerForFileAction extends AbstractUniversalGenerateAction {

    public DeleteElementAnnotationSwaggerForFileAction(String text) {
        super(text);
    }

    public DeleteElementAnnotationSwaggerForFileAction() {
        super();
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        WriteContext writeContext = WriteContextActionBuilder.create(anActionEvent);

        WriteCommandActionUtils.runWriteCommandAction(writeContext.fetchProject(), () -> {
            CommandCommandTypeProcessorFactory.decide(writeContext, WriteCommand.Command.DELETE_ANNOTATION_SWAGGER).writeFile();
        });
    }

    @Override
    public void update(@NotNull AnActionEvent anActionEvent) {
        super.update(anActionEvent);

        if (GlobalSettingsStateHelper.getInstance().annotationSwaggerEnabledShowed(anActionEvent.getProject())) {
            anActionEvent.getPresentation().setEnabled(true);
            anActionEvent.getPresentation().setVisible(true);
            return;
        }
        anActionEvent.getPresentation().setEnabled(false);
        anActionEvent.getPresentation().setVisible(false);
    }

    @Override
    protected ActionEnum fetchActionEnum() {
        return ActionEnum.DELETE_ANNOTATION_SWAGGER_FOR_FILE;
    }
}
