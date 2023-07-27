package cc.bitky.jetbrains.plugin.universalgenerate.constants;

import lombok.Getter;

/**
 * @author bitkylin
 */
public enum LocalizationEnum {

    TEST("Test", "测试"),

    // region --------- 通知 -----------

    NOTIFICATION_TITLE("Execution Exception", "执行发生异常"),

    // endregion

    // region --------- UI -----------

    SETTING_DISPLAY_NAME("Bitkylin Universal Generate", "Bitkylin 通用生成"),

    LABEL_LANGUAGE("language", "语言"),

    RADIO_BUTTON_LANGUAGE_ENGLISH("English", "英文"),

    RADIO_BUTTON_LANGUAGE_CHINESE("Chinese", "中文"),

    // endregion

    // region --------- 异常描述 -----------

    CLASS_NOT_FOUND("The specified class could not be found", "找不到指定的类"),
    ANNOTATION_TAG_ERROR("Incorrect information about @Tag annotations", "@Tag注解信息有误"),
    CLASS_ROLE_UNSUPPORTED("Class roles are not supported", "类的角色不被支持"),
    COMMAND_SCOPE_UNSUPPORTED("The current command scope is not supported", "当前的指令作用范围不被支持"),
    ELEMENT_NOT_SELECT("No element selected, command not executed", "未选择任何元素，指令未执行"),

    // endregion

    // region --------- Action & Action Group -----------

    // region --------- Action Group -----------

    EMPTY("empty space", "空白的"),
    ANNOTATION_TO_JAVA_DOC("Annotation to JavaDoc", "注解转JavaDoc"),
    GENERATE_ENTRY_ANNOTATION("Generate Entry Annotation", "生成入口注解"),
    FULL_DOCUMENT("Full Document", "整个文档"),
    CURRENT_ELEMENT("Current Element", "当前元素"),
    DUMB_MODE("Dumb Mode", "哑模式"),

    // endregion

    // region --------- Action -----------

    RE_GENERATE_TO_JAVA_DOC("Re-Generate to JavaDoc", "合并到JavaDoc"),
    POPULATE_MISSING_ANNOTATION("Populate Missing Annotation", "填充缺失的注解"),
    POPULATE_MISSING_JAVA_DOC("Populate Missing JavaDoc", "填充缺失的JavaDoc"),
    RE_GENERATE_ANNOTATION("Re-Generate Annotation", "重新生成注解"),

    // endregion

    // endregion

    ;

    @Getter
    private final String english;

    @Getter
    private final String chinese;

    LocalizationEnum(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }
}