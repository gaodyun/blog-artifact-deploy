# 将Github Action打包出来的Artifact部署到Nginx
框架：springboot

功能：到指定的仓库获取github action打包好的artifacts，根据时间顺序倒序排列，取最新的一个，部署到nginx

## 配置项
```yaml
# application.yml
# 将该文件放到jar包所在目录即可

logging:
  file:
    path: logs  # 日志文件的路径
github:
  owner: owner  # github owner
  repo: repo  # 仓库名称
  private: false  # 是否是私有仓库  
  token: token  # 私有仓库需要配置正确的token
artifact:
  name: artifact.zip  # 下载到本地的artifact文件名
shell:
  path: /blog_artifact_update.sh  # shell脚本的路径，，建议配置为绝对路径
  name: blog  # 博客名称，可配置为任意值，只用于日志打印
  nginx: /blog  # nginx静态资源文件夹，建议配置为绝对路径
  artifact: /artifact.zip  # artifact路径，需要带上文件名和后缀，建议配置为绝对路径
```
## 实现思路
目前的实现是，java把artifact下载下来，然后调用shell脚本去解压替换

这个比较危险，shell脚本里面有`rm -rf`的操作，一不小心容易`rm -rf /`，慎用
## 将springboot程序加入到系统服务中，设置为开机自启动
参考[Java CMS - Halo](https://docs.halo.run/install/linux)的文档，`Linux环境部署`章节的`作为服务运行`小节