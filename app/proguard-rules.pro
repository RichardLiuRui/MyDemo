# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-allowaccessmodification
-dontpreverify
-dontoptimize
-dontwarn
-flattenpackagehierarchy
-optimizationpasses 5
# The remainder of this file is identical to the non-optimized version
# of the Proguard configuration file (except that the other file has
# flags to turn off optimization).
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-renamesourcefileattribute Proguard
-keepattributes Signature,SourceFile,LineNumberTable
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-keepattributes InnerClasses,EnclosingMethod
-verbose

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# The onEvent methods must not renamed because they are accessed using reflection
#-keepclassmembers class ** {
#    public void onEvent*(***);
#}
#event bus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keepclassmembers,includedescriptorclasses class ** { public void onEvent*(**); }


# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}



# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class **.R$* {
    *;
}
# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version. We know about them, and they are safe.

-keep class * extends android.view.View{*;}
-keep class * extends android.app.Dialog{*;}
-keep class * implements java.io.Serializable{*;}

-keep class com.android.** {*;}
-keep class com.google.** {*;}
-keep class com.squareup.** {*;}
-keep class com.umeng.** {*;}

-keep class android.support.** {*;}
-dontwarn android.support.**
-keep class com.liurui.domain.beans.** {*;}

-keep class com.fernandocejas.frodo.**{*;}
-dontwarn com.fernandocejas.frodo.**
-keepclasseswithmembers class * {
    @com.fernandocejas.frodo.annotation.* <methods>;
}

-keep class android.support.** {*;}

# banner 的混淆代码
-keep class com.youth.banner.** {*;}

#腾讯IM
-keep class com.tencent.**{*;}
-dontwarn com.tencent.**

-keep class tencent.**{*;}
-dontwarn tencent.**

-keep class qalsdk.**{*;}
-dontwarn qalsdk.**

-keep class com.huawei.android.pushagent.**{*;}
-keep class com.huawei.android.pushselfshow.**{*;}
-keep class com.huawei.android.microkernel.**{*;}
-keep class com.baidu.mapapi.**{*;}

#------------------小米推送----------------------------------
#这里 com.tencent.imsdk.MiPushMessageReceiver 改成 App 中定义的完整类名
-keep class com.ucicsh.NoraShop.Express.widget.MiPushMessageReceiver {*;}
#可以防止一个误报的 warning 导致无法成功编译，如果编译使用的 Android 版本是 23。
-dontwarn com.xiaomi.push.**
#------------------华为推送----------------------------------
-keep class com.huawei.android.**{*;}
-dontwarn com.huawei.android.**
-keep class com.baidu.mapapi.**{*;}
-dontwarn com.baidu.mapapi.**
#------------------友盟----------------------------------
-keep public class com.ucicsh.NoraShop.Express.R$*{
public static final int *;
}
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**

#------------------AndPermission----------------------------------
-dontwarn com.yanzhenjie.permission.**

#------------------Glide----------------------------------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-ignorewarnings