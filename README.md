# Wurst I18n Plugin

这是一个为Wurst客户端(Minecraft 1.21.11)开发的国际化(I18n)插件，提供中文翻译支持。

## 功能特性

- 为Wurst客户端提供中文界面翻译
- 支持Minecraft 1.21.11版本
- 基于Fabric Mod Loader开发
- 完整的Mixin集成

## 项目结构

```
wursti18nplugin-template-1.21.11/
├── src/
│   ├── main/          # 主模块代码
│   └── client/        # 客户端模块代码
├── libs/              # 依赖库目录
├── build.gradle       # Gradle构建配置
└── README.md          # 项目说明文档
```

## 环境要求

- Java 21 或更高版本
- Gradle 8.0+ 或使用项目自带的gradlew
- Minecraft 1.21.11
- Wurst Client v7.52 for MC1.21.11

## 安装与构建

### 1. 获取Wurst客户端JAR文件

**重要：** 你需要手动获取Wurst客户端的JAR文件并放置在`libs/`目录中。

1. 从[Wurst官方网站](https://www.wurstclient.net/)下载Wurst Client v7.52 for MC1.21.11
2. 将下载的JAR文件重命名为：`Wurst-Client-v7.52-MC1.21.11.jar`
3. 将文件复制到项目的`libs/`目录中

### 2. 构建项目

使用以下命令构建项目：

```bash
# Windows
gradlew build

# Linux/Mac
./gradlew build
```

构建完成后，生成的mod文件将位于`build/libs/`目录中。

### 3. 安装到Minecraft

1. 将生成的`wursti18nplugin-1.0.0.jar`文件复制到Minecraft的`mods/`目录
2. 确保已安装Fabric Loader
3. 启动Minecraft并选择Fabric配置文件

## 配置说明

### build.gradle 配置

项目已经配置好对Wurst客户端JAR的依赖。在`build.gradle`文件的`dependencies`部分，你可以看到：

```gradle
dependencies {
    // ... 其他依赖
    modImplementation files("libs/Wurst-Client-v7.52-MC1.21.11.jar")
    implementation "com.google.code.gson:gson:2.13.2"
}
```

**注意：** 如果你使用的Wurst版本不同，需要修改以下部分：

1. **JAR文件名**：如果Wurst JAR文件名不同，需要更新`build.gradle`中的引用：
   ```gradle
   modImplementation files("libs/你的-Wurst-JAR-文件名.jar")
   ```

2. **Minecraft版本**：如果需要适配其他Minecraft版本，需要修改`gradle.properties`中的相关配置。

### gradle.properties 配置

主要配置项包括：
- `minecraft_version`: Minecraft版本
- `mod_version`: 插件版本
- `maven_group`: Maven组ID
- `archives_base_name`: 输出文件基础名称

## 开发指南

### 添加新的翻译

1. 编辑`src/main/resources/assets/wursti18nplugin/translations/zh_cn.json`文件
2. 按照JSON格式添加新的翻译条目
3. 重新构建项目

### 调试

使用以下命令运行开发环境：

```bash
# 运行Minecraft客户端
gradlew runClient

# 运行Minecraft服务器
gradlew runServer
```

## 文件说明

- `src/main/java/com/wursti18nplugin/Translator.java` - 翻译器核心类
- `src/main/java/com/wursti18nplugin/WurstI18nPlugin.java` - 插件主类
- `src/client/java/com/wursti18nplugin/WurstI18nPluginClient.java` - 客户端模块
- `src/main/resources/fabric.mod.json` - Fabric Mod配置
- `src/main/resources/wursti18nplugin.mixins.json` - Mixin配置

## 许可证

本项目基于MIT许可证开源。详见[LICENSE](LICENSE)文件。

## 贡献

欢迎提交Issue和Pull Request来改进这个项目。

## 问题排查

### 常见问题

1. **构建失败：找不到Wurst JAR文件**
   - 确保`libs/Wurst-Client-v7.52-MC1.21.11.jar`文件存在
   - 检查文件名是否与`build.gradle`中的引用一致

2. **游戏崩溃或Mod不工作**
   - 确保Minecraft版本为1.21.11
   - 确保Wurst客户端版本为v7.52
   - 检查Fabric Loader是否正确安装

3. **翻译不显示**
   - 检查翻译文件格式是否正确
   - 确保mod已正确加载

## 更新日志

### v1.0.0
- 初始版本发布
- 支持Wurst Client v7.52 for MC1.21.11
- 提供基础中文翻译