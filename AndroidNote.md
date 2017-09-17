9.17
安卓四层架构
Linux内核层，系统运行库层，应用框架层，应用层

安卓四大组件
活动，应用的脸面
服务，后台的工作
广播接收器，应用之间互相接发消息
内容提供器，应用之间共享数据

Project模式下的项目结构
.gradle和.idea,Android studio自动生成，不可手动编辑

app，项目中的代码，资源
	build，包含编译时自动生成的文件
	libs，存放第三方jar包
	androidTest，编写测试用例
	java，存放所有java代码
	res，存放图片，布局，字符串，包含drawable、layout、values目录
	AndroidManifest.xml，配置文件，四大组件都要在此注册，还可以添加权限声明
	test，编写unit test测试用例
	.gitignore，将app模块内指定文件排除在版本控制外
	app.iml，自动生成文件
	build.gradle，app模块的gradle构建脚本
	proguard-rules.pro，制定代码混淆规则

build，编译时自动生成的文件
gradle，gradle wrapper配置文件
.gitignore，将指定文件排除在版本控制之外
build.gradle，全局gradle构建脚本
gradle.properties，全局gradle配置文件
gradlew和gradlew.bat，命令行界面执行gradle命令
.iml，intellij ides自动生成文件
local.properties,指定sdk路径
setting.gradle，制定项目中所有引入模块

一个项目的运行规则
所有活动都要在AndroidManifest.xml里面注册，包含在<intent-filter>和</intent-filter>里面的代码制定主活动，即点击图标之后首先启动的活动
应用中所有看到的东西都是放在活动之中的
所有活动（Activity）都是继承自AppCompatActivity，这个类又是Activity的子类，这样继承下来项目的活动才有活动的特性
界面一般都是在布局文件（xx_layout.layout）中编写的，然后通过setContentView引入到活动中

项目中的资源
在res目录下的几个文件夹：
drawable，存放图片
mipmap，存放应用图标
values，存放字符串、样式、颜色等配置
layout，存放布局文件
如何使用？
举例：res/values/strings.xml中，有如下内容：
<resources>
	<string name="app_name">HelloWorld</string>
</resources>
可通过两种方法引用：代码中通过R.string.app_name,或者XML中通过@string/app_name引用
这实际上是一种键值对的引用方式，通过键引用值的方式更快捷的得到值

build.gradle详解
gradle是一个用于构建项目的工具
外层的build.gradle：
有两处repositories闭包，声明了jcenter配置，用于托管开源项目到jcenter中
dependencies闭包中使用classpath声明一个gradle插件，用于构建Android项目
app模块中的build.gradle：
apply plugin——com.android.application和com.android.library
android闭包——compileSdkVersion指定编译版本
	     buildToolsVersion指定项目构建工具版本
	     defaultConfig闭包{applicationId指定项目包名，minSdkVersion指定最低兼容版本，targetSdkVersion目标版本，versionCode版本号，versionName版本名}
	     buildTypes闭包{debug指定生成测试版安装文件配置，release指定生成正式版安装文件配置}
dependencies闭包——制定项目所有依赖关系，分为本地依赖，库依赖和远程依赖

日志工具——logcat的使用
日志工具类android.util.Log中的五种方法：
Log.v()，verbose
Log.d()，debug
Log.i()，info
Log.w()，warn
Log.e()，error
具体使用方法：
Log.d("HelloWorldActivity", "onCreate execute");
[Log.方法名("活动名","想打印的信息")]
