# Drawer-Behavior
[ ![Download](https://api.bintray.com/packages/infideap2/Drawer-Behavior/Drawer-Behavior/images/download.svg) ](https://bintray.com/infideap2/Drawer-Behavior/Drawer-Behavior/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Drawer--Behavior-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6239)

Drawer behavior is a library use **[Android DrawerLayout Support library](https://developer.android.com/training/implementing-navigation/nav-drawer)** as **Parent Class [Easy to migrate]**, that provide an **extra behavior on drawer**, such as, move view or scaling view's height while drawer on slide. 

If current project use **Android DrawerLayout Support library** and kinda boring with the effect. Then, just **change the layout code** and **calling** necessary **method for animation/effect**.

## New update
 * New drawer class with 3D effect
 * Card Effect 
 
## Features
 * Zoom in & Zoom out effect
 * X-Translation effect
 
![Alt Text](https://raw.githubusercontent.com/shiburagi/Drawer-Behavior/preview/gif/preview1.gif)

**Android 9.0+ support**

---


<a href='https://ko-fi.com/A0A0FB3V' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi4.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>
[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=D9JKYQL8452AL)

## Including In Your Project

If you are a Maven user you can easily include the library by specifying it as
a dependency:

#### Maven
``` xml
<dependency>
  <groupId>com.infideap.drawerbehavior</groupId>
  <artifactId>drawer-behavior</artifactId>
  <version>0.0.5</version>
  <type>pom</type>
</dependency>
```
#### Gradle
```groovy
dependencies {
   implementation 'com.infideap.drawerbehavior:drawer-behavior:0.0.5'
}
```

if **the gradle unable to sync**, you may include this line in project level gradle,
```groovy
repositories {
 maven{
   url "https://dl.bintray.com/infideap2/Drawer-Behavior"
 }
}
```

**or**,
you can include it by **download this project** and **import /drawerbehavior** as **module**.

## How to use
**Creating the layout**

### Advance Drawer Layout
---
```xml
<com.infideap.drawerbehavior.AdvanceDrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    android:background="@color/colorWhite"
    tools:openDrawer="start">

    <include
        layout="@layout/app_bar_default"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        android:background="@color/colorWhite"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" />

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view_notification"
        android:background="@color/colorPrimary"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="end"
        android:fitsSystemWindows="false">
        <include layout="@layout/content_notification"/>
    </android.support.design.widget.NavigationView>

</com.infideap.drawerbehavior.AdvanceDrawerLayout>
```

**Initialize**
```java
drawer = (AdvanceDrawerLayout) findViewById(R.id.drawer_layout);
```

**Use custom behavior**
```java
drawer.useCustomBehavior(Gravity.START); //assign custom behavior for "Left" drawer
drawer.useCustomBehavior(Gravity.END); //assign custom behavior for "Right" drawer 
```
---

#### Card Effect

![Alt Text](https://raw.githubusercontent.com/shiburagi/Drawer-Behavior/preview/gif/preview-card-1.gif)


```java
drawer.setRadius(Gravity.START, 25);//set end container's corner radius (dimension)
```

---

### Advance 3D Drawer Layout
---
![Alt Text](https://raw.githubusercontent.com/shiburagi/Drawer-Behavior/preview/gif/preview-3d-2.gif)

```xml
<com.infideap.drawerbehavior.Advance3DDrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    android:background="@color/colorPrimary"
    tools:openDrawer="start">

    <include
        layout="@layout/app_bar_default"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        android:theme="@style/ThemeOverlay.AppCompat.Dark"
        android:background="@color/colorPrimary"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" />

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view_notification"
        android:background="@color/colorPrimary"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="end"
        android:fitsSystemWindows="false">
        <include layout="@layout/content_notification"/>
    </android.support.design.widget.NavigationView>

</com.infideap.drawerbehavior.Advance3DDrawerLayout>
```

**Initialize**
```java
drawer = (Advance3DDrawerLayout) findViewById(R.id.drawer_layout);
```

**Use custom behavior**
```java
drawer.setViewRotation(Gravity.START, 15); // set degree of Y-rotation ( value : 0 -> 45)
```
---

**Customize**
```java
drawer.setViewScale(Gravity.START, 0.9f); //set height scale for main view (0f to 1f)
drawer.setViewElevation(Gravity.START, 20);//set main view elevation when drawer open (dimension)
drawer.setViewScrimColor(Gravity.START, Color.TRANSPARENT);//set drawer overlay coloe (color)
drawer.setDrawerElevation(Gravity.START, 20);//set drawer elevation (dimension)

drawer.setRadius(Gravity.START, 25);//set end container's corner radius (dimension)
```

## Contact
For any enquiries, please send an email to tr32010@gmail.com. 

## License

    Copyright 2018 Shiburagi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
