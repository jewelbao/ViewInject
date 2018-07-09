# ViewInject
A tool for injecting views

[ ![Download](https://api.bintray.com/packages/jewelbao88/ComponentsMaven/ViewInject/images/download.svg) ](https://bintray.com/jewelbao88/ComponentsMaven/ViewInject/_latestVersion)
<a href='https://bintray.com/jewelbao88/ComponentsMaven/ViewInject/_latestVersion'><img src='https://api.bintray.com/packages/jewelbao88/ComponentsMaven/ViewInject/images/download.svg'></a>

##  详细特性
- 无缝注入View到主体中(Activity、Fragment、View)
- 支持View注入、布局文件注入
- 默认key-value缓存注入View和被注入主体，也可自实现缓存策略
- 被注入主体为View时，注入View拥有被注入主体相同LayoutParams
- 被注入主体为Fragment时，注入View为Fragment'onCreateView返回的View
- 被注入主体为Activity时，注入View以自身LayoutParams填充到Activity.
- 重复注入View时，会移除View的父View并根据缓存还原被注入主体，也可在自实现缓存策略中重写该逻辑