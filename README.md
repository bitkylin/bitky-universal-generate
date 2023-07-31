# Bitkylin Universal Generate

![Build](https://github.com/bitkylin/bitkylin-universal-generate/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Adjust the [pluginGroup](./gradle.properties), [plugin ID](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [ ] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the `PLUGIN_ID` in the above README badges.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->

### What's this?

- For Controller class or POJO class, you can easily generate Protostuff annotations ( @Tag ) with one click and convert JavaDoc to Swagger annotations ( @Api, @ApiOperation, @ApiModel, @ApiModelProperty ) with one click. In turn, you can convert Swagger annotations to JavaDoc with a single click.

### 做什么呢？

- 作用于 Controller 类或 POJO 类，你可以很方便的一键生成 Protostuff 注解 ( @Tag )，并且可以一键直接将 JavaDoc 转换为 Swagger 注解 ( @Api, @ApiOperation, @ApiModel, @ApiModelProperty )。反过来，你也可以一键将 Swagger 注解转换为 JavaDoc。

### Features

1. You can generate Swagger annotations ( @Api, @ApiOperation, @ApiModel, @ApiModelProperty ) with one click, and Protostuff annotations ( @Tag ) with one click via the right-click menu.
2. you can through the right-click menu , one-click Swagger annotations converted to JavaDoc annotations.
3. With the cursor pointing to the name of a class, field or method, right-clicking Execute allows you to process only that element. When the cursor is pointing elsewhere, right-click to perform unified processing for all elements in the current file.
4. Currently supports two languages, English and Chinese, you can switch through the (settings - tools - Bitkylin Universal Generate) page.
5. If you only need to manipulate Swagger annotations but not Protostuff annotations , and vice versa, don't worry, you can switch through the (settings - tools - Bitkylin Universal Generate) page.

### 特性

1. 你可以通过右键菜单，一键生成Swagger注解 ( @Api, @ApiOperation, @ApiModel, @ApiModelProperty ), 一键生成Protostuff注解 ( @Tag )。
2. 你可以通过右键菜单，一键将 Swagger 注解转换为 JavaDoc 注释。
3. 光标指向类、字段或者方法的名字，点击右键执行，可以仅针对该元素进行处理。当光标指向其他地方，点击右键，可以对当前文件中的所有元素进行统一处理。
4. 当前支持两种语言，英文和中文，可以通过 (settings - tools - Bitkylin Universal Generate) 页面进行切换。
5. 如果你只需要操作 Swagger 注解而不需要操作 Protostuff 注解，反之亦然，不用担心，你可以通过 (settings - tools - Bitkylin Universal Generate) 页面进行切换。

### Tip.

#### NO.1

Class name suffixed with ( Controller ), or with ( @Controller, @RestController ) annotation, you will add ( @Api, @ApiOperation ) annotation when you execute the instruction. Classes with Lombok annotations ( @Data, @Getter, @Setter ) will add ( @ApiModel, @ApiModelProperty, @Tag ) annotation when you execute the instruction.

类名后缀为 ( Controller ),或者有 ( @Controller、@RestController ) 注解，你在执行指令时，会添加 ( @Api, @ApiOperation ) 注解。类上有Lombok注解 ( @Data、@Getter、@Setter )，你在执行指令时，会添加 ( @ApiModel, @ApiModelProperty, @Tag ) 注解。

#### NO.2

If you want to generate @Tag annotations for the whole document with a customized starting number, just set the starting number in the first field of the class and execute (Generate Entry Annotation - Full Document) via the right-click menu, See the next section: Class Format Examples.

如果你想按照自定义的起始序号，生成整个文档的 @Tag 注解，只需要在类中的第一个字段设置起始序号即可，然后通过右键菜单执行(生成入口注解 - 整个文档) ，参见下一节：类格式示例。

### Class Format Examples

#### POJO

```java
import io.protostuff.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TestRequest {
    @Tag(105)
    private String name;
    /**
     * bitkylin age
     */
    private Integer age;
    private Address address;

    @Setter
    @Getter
    public static class Address {
        @Tag(208)
        private String province;
        private String city;
        private String detailedAddress;
    }
}
```

#### controller

```java
/**
 * test desc
 *
 * @author bitkylin
 */
@RestController
@RequestMapping('/test')
public class TestController {
    /**
     * test api one
     */
    @PostMapping('/api-one')
    public String apiOne(@RequestBody TestRequest request) {
        return 'success';
    }
}
```

### 感谢

感谢以下项目提供的灵感与实现方式：

- https://github.com/starcwang/easy_javadoc
- https://github.com/EverSpring/swagger-tool
- https://github.com/huangbaihua001/RestfulToolkitX

<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Bitkylin Universal Generate"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/bitkylin/bitkylin-universal-generate/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation