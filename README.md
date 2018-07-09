# ViewInject
A tool for injecting views

[ ![Download](https://api.bintray.com/packages/jewelbao88/ComponentsMaven/ViewInject/images/download.svg) ](https://bintray.com/jewelbao88/ComponentsMaven/ViewInject/_latestVersion)

##  详细特性
- 无缝注入View到主体中(Activity、Fragment、View)
- 支持View注入、布局文件注入
- 默认key-value缓存注入View和被注入主体，也可自实现缓存策略
- 被注入主体为View时，注入View拥有被注入主体相同LayoutParams
- 被注入主体为Fragment时，注入View为Fragment'onCreateView返回的View
- 被注入主体为Activity时，注入View以自身LayoutParams填充到Activity.
- 重复注入View时，会移除View的父View并根据缓存还原被注入主体，也可在自实现缓存策略中重写该逻辑

## 使用方式

1、在 build.gradle 中添加依赖

```
compile 'com.jewel.util:ViewInject:1.0'
```

2、在Activity/Fragment/View中注入View

```
final View loadingView = getLayoutInflater().inflate(R.layout.layout_loading, null);

ViewInjectFactory.getInstance().inject(MainActivity.this, loadingView);

ViewInjectFactory.getInstance().inject(AFragment.this, loadingView);

ViewInjectFactory.getInstance().inject(button, loadingView);
```

或者全局注入布局文件

```
ViewInjectFactory.getInstance().inject(MainActivity.this, R.layout.layout_loading);

ViewInjectFactory.getInstance().inject(AFragment.this, R.layout.layout_loading);

ViewInjectFactory.getInstance().inject(button, R.layout.layout_loading);
```

3、移除注入的View

```
ViewInjectFactory.getInstance().revert(MainActivity.this);

ViewInjectFactory.getInstance().revert(AFragment.this);

ViewInjectFactory.getInstance().revert(button);
```

4、销毁缓存数据（建议在onDestory()或者onDestoryView()中使用）

```
@Override
protected void onDestroy() {
    ViewInjectFactory.getInstance().destroy(button, MainActivity.this);
    // or
    ViewInjectFactory.getInstance().destroy(button);
    ViewInjectFactory.getInstance().destroy(MainActivity.this);
    super.onDestroy();
}

```


## License

```
Copyright 2018 jewelbao

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```