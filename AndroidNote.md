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

9.24学习笔记
活动是一个包含用户界面的组件

创建活动的步骤
创建一个不添加任何活动的项目，右击包名->new->activity->empty activity,命名活动，不勾选generate layout file和launcher activity选项[genarate layout file表示创建对应的布局文件，launcher Activity表示设置为项目的主活动]

创建和加载布局
每一个布局对应一个活动，布局显示界面内容
右击app/src/main/res目录->new->directory创建名为layout的布局目录，右击此目录->new->layout resource file，命名布局文件，根元素选择linearlayout

在布局中添加按钮
在xml文件中添加代码达到增加相应控件的目的
在linearlayout元素中添加代码：
<Button
	android:id="@+id/button_1"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:text="Button_1"
	/>
@+id/id_name:定义控件id
layout_width && layout_height:定义控件大小
text：显示在控件上的文字

为活动加载布局
在活动文件中的oncreate方法之中通过调用setContentView(R.layout.layoutname)的方法为当前的活动添加一个已经写好的布局

在AndroidManifest.xml中进行活动的注册
所有的活动都要在AndroidManifest.xml文件之中进行注册
活动的注册声明放在<application>标签之中，通过<activity>标签进行注册
仅仅进行注册仍不能运行程序，因为要指定主活动。
在<activiyty>标签之中加入<intent-filter>标签添加<action android:name="android.intent.action.MAIN"/>和<category android:name="android.intent.category.LAUNCHER"/>两句声明即可,这两句声明包含在哪个活动的<activity>标签之中，这个活动就是指定的主活动

在活动之中使用toast
toast就是一个小的位于屏幕底部的弹窗
定义toast的触发点
在onCreate方法之中添加代码
Button button1 = (Button) findViewById(R.id.button_1);
button1.setOnclickListener(new View.OnclickListener()
{
	@Override
		public void onClick(View v){
		Toast.makeText(FirstACtivity.this, "hello",
	Toast.LENGTH_SHORT).show();
	}
});
findViewById:根据id寻找到相应的控件，该方法得到的是一个View对象，需要类型转换成相应的控件类型
setOnClickListener方法为按钮注册监听器，在onClick方法中重写
makeText方法创建一个toast对象，调用show方法进行显示，makeText的三个参数分别为Context，一般传入活动本身，第二个参数为文本显示的内容，第三个是toast显示的时长

在活动之中使用menu
在res目录下创建menu文件夹，在此文件夹之下创建菜单文件，此时就能得到一个用于定义menu的XML文件
在这个xml文件之中，有一个<menu>元素，在这个元素标签之中就可以定义menu内容，例如以下代码
<item
	android:id="@+id/add
	android:title="add"/>
<item
	android:id="@+id/remove
	android:title="remove"/>
就可以在菜单中添加add和remove选项
在活动文件之中就可以重写onCreateOptionsMenu()方法,通过getMenuInflater方法得到MenuInflater对象，再调用inflater方法，传入资源文件即xml文件参数以及将菜单项添加到的Menu对象参数，即可创建Menu
定义菜单响应事件
重写onOptionsItemSelected方法，通过简单的switch语句可以定义每一个menu选项所对应的事件

销毁活动
在相应的逻辑代码之中调用finish()方法即可

使用Intent变换当前的活动

使用显式Intent
建立一个新的活动，命名为secondactivity,并且在AndroidManifest.xml中进行活动的注册，可以根据需要修改对应的layout.xml文件
Intent是各组件之间进行交互的重要方式，可以指明当前组件想要执行的操作，还可以在组件之间传递数据
Intent的其中一个构造函数为Intent(Context packageContext, Class<?> cls),第一个参数指定启动活动的上下文，第二个参数为想要启动的活动，在构建好这个Intent之后通过startActivity()传入该Intent即可启动目标活动,如
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
startActivity(intent);

使用隐式Intent
隐式Intent制定一系列action和catagory等信息，交由系统分析Intent并找到合适的活动
通过在AndroidManifest.xml中配置相应活动的intent-filter，指定该活动的action和category标签，就可以在隐式intent中找到与之匹配的活动并启动该活动

隐式Intent的其他用法
启动其他程序的活动，例如展示一个网页
使用setData在Intent中通过Uri.parse载入网址字符串，即可打开系统浏览器并浏览网站，这个步骤也可以在<intent-filter>中通过对<data>标签进行配置，<data>标签一般包括android:scheme,android:host,android:port,android:path,android:mineType几个内容,即可通过intent进行调用

向下一个活动传递数据
Intent提供putExtra方法的重载，把数据暂存在Intent中，启动下一个活动之后就可以从这个Intent中提取出数据
putExtra方法有两个参数，一个是键，用于后面的取值，第二个才是要传递的数据
在被启动的活动中，定义一个Intent = getIntent()， Intent.getStringExtra等方法取得数据即可

向上一个活动传递数据
startActivityForResult()方法用于在活动销毁时返回一个结果给上一个活动，它接受两个参数，Intent和请求码，用于在回调的时候检查数据来源
如果用户按下back键返回上一个活动，就要在当前活动中重写onBackPressed方法来配置Intent
