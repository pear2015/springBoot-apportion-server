# 分析研判子系统外部接入服务端
ecms-analysisgauge-access是个java工程，作为分析研判子系统的中转服务，通过spring mvc提供的rest api向外部系统公布数据，同时以rest api的方式从外部系统获得数据，再中转给分析研判子系统。


## 一. 工作环境
### 1. 基础环境

* 需要安装JDK 1.8。安装包地址：`\\172.18.3.26\共享资源\java\jdk`

* 推荐选装Intellij IDEA 16.2.1。安装包地址：`\\172.18.3.26\共享资源\java\IDE\intellij idea`

* SonarLint:`\\172.18.3.26\共享资源\java\IDE\intellij idea\idea-plugins\SonarLint-2.2.zip`

* 需要安装Tomcat 8.5.5。安装包地址：`\\172.18.3.26\共享资源\java\web-server\tomcat`

* 需要安装Gradle 3.2.1。安装包地址：`\\172.18.3.26\共享资源\java\build-tools\gradle`

### 2. 快速指南
* 先将工程代码clone到本地
* 打开IDEA，使用“import project”
* 下一步,选择Import project from external model,选择Greadle
* 下一步,选择以下三项
    * Use auto-import
    * Create directories for ....
    * Use default gradle wrapper(recommended)
* 在命令行运行 `gradlew build` 构建项目
* 右键`\backend\configs\Application.java`运行项目 或 使用Tomcat宿主运行。
* 常用gradle命令
gradle clean  或gradlew  clean          -- 清理
gradle build  或gradlew  build          -- 编译
gradle build --refresh-dependencies     --编译并下载最新依赖包（如果idea刷新功能无法下载，则编译带上此命令后，再刷新即可）

### 3. rest api 说明

* 参考 站点前缀：
    ```
    http://ip:host/api/v1/
    http://analysisgauge.gsafety.com/api/v1/

    ```

## 二、项目组织的思路
如果项目规模较大，需要分级进行部署，业务方面有较多的层级之间的交互。因此会有较多的业务服务（Web API），且有互相依赖和通信的场景。为了让每个业务服务保持一定
的独立性、自维护的特征，工程规划上基于业务模块优先的原则，且保证业务模块内部的变化尽可能的对使用者的影响最小化。


*  模块优先，每个系统都由若干个业务模块组成。
*  每个业务模块由一个services工程和一个WebApi工程组成，每个工程是一个jar包。
aneid-cirme_record-common、aneid-cirme_record-contract、aneid-cirme_record-service、
aneid-cirme_record-webapi分别编译并publish到仓库
*  Services工程用于实现业务逻辑。
*  WebApi工程用于向外部提供访问接口，自身不应包含任何业务逻辑的实现。


## 三、组件的工程目录结构


```
aneid-cirme_record-workspace/            - 业务子系统的根目录
	├─ gradle/                         - gradle工作目录，存放一些gradle命令和gradle-wrapper包。
	├─ .idea/                          - idea工作目录，存放了idea项目的一些描述文件等。
	├─ .build/                         - gradle构建结果的输出目录。
	├─ .gitgnore                       - git忽略文件
	├─ docs                            - 项目相关文档
	├─ scripts                         - 通用脚本组件(git,publish,sonar,jacoco...)
	├─ aneid-cirme_record-backend      - 工程宿主程序
	├─ aneid-cirme_record-contract     - aneid-cirme_record业务模块契约，包含服务和数据契约
	├─ aneid-cirme_record-service      - aneid-cirme_record业务模块服务实现，含此业务模块对应的datamapper,entity,repository,serviceimpl
	├─ aneid-cirme_record-webapi       - aneid-cirme_record业务模块webapi-controller
	├─ aneid-cirme_record-common       - aneid-cirme_record系统公共模块
	├─ gradlew                         - Gradle start up script for UN*X
	├─ gradlew.bat                     - Gradle startup script for Windows
	├─ build.gradle                    - gradle构建脚本
	└─ README.markdown                 - *每个组件必须提供一个README文件，对项目进行描述*
```

在开发时应使用gradle作为构建工具，并添加gradle java plugin。
```
apply plugin: "java"
```
*由于java插件已经约定了一种默认的工程结构，开发时应该遵守这种工程结构。*

#### Services工程和WebApi工程(idea + gradle + gradle java plugin)
```
aneid-cirme_record-service/       - 业务模块的根目录
	├─ gradle/                  - gradle工作目录，存放一些gradle命令和gradle-wrapper包。
	├─ .idea/                   - idea工作目录，存放了idea项目的一些描述文件等。
	├─ .build/                  - gradle构建结果的输出目录。
	├─ src/                     - 源代码目录（该目录中的目录结构是由gradle java plugin约定的）
	|  ├─main/                  - 实现代码
	|  |  ├─java/               - java代码
	|  |  └─resources/          - 静态资源文件
	|  ├─test/                  - 测试代码
	|  |  ├─java/               - java代码
	|  |  └─resources/          - 静态资源文件
	├─ .gitgnore                - git忽略文件
	├─ gradlew                  - Gradle start up script for UN*X
	├─ gradlew.bat              - Gradle startup script for Windows
	├─ build.gradle             - gradle构建脚本
	└─ README.markdown          - *每个组件必须提供一个README文件，对项目进行描述*
```

## 四、组件内部名字空间的划分（package）
按照项目组织的思路，每个业务模块通常由两个java工程构成：一个是services，用于实现业务逻辑；另一个是WebApi，
用于向外部公开服务。

#### 1. services工程
```
	src/                                                                                - 源代码目录（该目录中的目录结构是由gradle java plugin约定的）
	 ├─main/                                                                          - *实现代码*
	 |  ├─java/                                                                       - java代码
	 |  |  ├─com/gsafety/aneid/crime_record/service/entity                            - 领域对象
	 |  |  ├─com/gsafety/aneid/crime_record/service/repository                        - 数据访问层
	 |  |  ├─com/gsafety/aneid/crime_record/service/datamapper                        - 实体<-->dto关系映射
	 |  |  ├─com/gsafety/aneid/crime_record/service/serviceimpl                       - 服务实现层
	 |  |  ├─com/gsafety/aneid/crime_record/services                                  - 服务包
	 |  └─resources/                                                                  - 静态资源文件
	 ├─test/                                                                          - 测试代码
	 |  ├─java/                                                                       - java代码
	 |  |  └─***Test.java                                                             - 测试代码文件
	 |  └─resources/                                                                  - 静态资源文件和配置文件
```

##### 在services中按照package划分为datamapper、entity、repository和serviceimpl ：
* datamapper用于数据实体entity和数据契约dto的转换。
* entity为数据映射实体
* repository为数据存储仓储服务，负责数据的CRUD操作。
* serviceimpl中定义业务逻辑实现类。

#### 2. WebApi工程
```
	src/                                                                                - 源代码目录（该目录中的目录结构是由gradle java plugin约定的）
	 ├─main/                                                                          - *实现代码*
	 |  ├─java/                                                                       - java代码
	 |  |  ├─com/gsafety/aneid/crime_record/webapi/controller                         - webapi
	 |  |
	 |  ├─resources/                                                                  - 静态资源文件(以平级的方式存放*.properties文件，不分文件夹)
	 ├─test/                                                                          - 测试代码
	 |  ├─java/                                                                       - java代码（应该一个Controller类，就对应一个测试类）
	 |  |  └─***Test.java                                                             - 测试代码文件
	 |  └─resources/                                                                  - 静态资源文件和配置文件
```

## 五、组件之间的依赖（引用）
首先每个工程都是一个gradle工程。在项目中不管是使用第三方jar包，还是使用业务模块的jar包都只能通过依赖jar包的方式进行，
不应采用项目依赖的方式。而且应使用gradle管理所有的依赖。

在某些情况下，特别是在开发过程中， 一个业务模块需要依赖别的业务模块时，由于组件还不稳定，开发人员之间应该协商好，将被依赖组件的版本设为SNAPSHOT提交到
maven仓库，依赖它的模块从仓库中获取这个SNAPSHOT版本。

有关如何在开发环境里将组件上传至maven私服（Nexus），请查看[如何上传组件到nexus](docs/how-to-upload-artifact-to-nexus-by-publish.markdown)。

## 六、maven的groupid、artifactid命名
1. Achilles中所有的业务模块组件的groupid都命名为：com.gsafety.ecms.analysisgauge.access
* artifactid命名规则为：aneid-crime_record-[modulename]-services 或 aneid-crime_record-[modulename]-webapi
一些更多的命名约定请查看：[工程中的一些命名约定](docs/projects-named-convention.markdown)。

## 七、版本号规则
1. 采用语义化命名规则，初始版本号为:1.0.0
* Releases版本规则为：1.0.0-RELEASES
* Snapshot版本规则为：1.0.0-SNAPSHOT

## 八、规范
* [开发过程控制](http://172.18.3.103/vNextDevTechs/Cooperation)
* [RestfulApi设计规范](http://172.18.3.103/vNextDevTechs/Cooperation)





  