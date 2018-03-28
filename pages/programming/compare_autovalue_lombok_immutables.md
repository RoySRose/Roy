---
title: AutoValue, Lombok, Immutables 비교하기
keywords: AutoValue, Lombok, Immutables, 비교
last_updated: March 28, 2018
created : March 28, 2018
tags: [programming]
summary: "AutoValue vs Lombok vs Immutables"
sidebar: mydoc_sidebar
permalink: compare_autovalue_lombok_immutables.html
folder: programming
---


## Lombok, AutoValue 및 Immutables 이란

Lombok, AutoValue 및 Immutables 은 모두 <a href="#" data-toggle="tooltip" data-original-title="{{site.data.glossary.hardboilerplate}}">*hard boiler plate code*</a>를 자동 생성해주는 툴입니다.

## annotation processing 을 통한 코드 생성 방식의 차

Lombok, AutoValue 및 Immutables는 모두 javac을 통해 annotation 을 사용하지만 Lombok이 annotation을 사용하는 방법은 AutoValue 및 Immutables가 사용하는 방법과 다릅니다.
AutoValue 및 Immutables 의 경우 일반적인 방식의 annotation 으로 사용을 하며 코드를 통해 코드를 생성하는 방식 사용하고 있습니다.
그렇기 때문에 AutoValue 및 Immutables 는 생성된 클래스가 기본 템플릿 클래스명과 다르며 실제로는 템플릿 소스를 extends 하여 생성된 코드이며 템플릿 소스와 생성된 소스가 하나의 프로젝트 안에서 사용되기에 용이합니다.

 * AutoValue  
![](../images/pages/compare_autovalue_lombok_immutables/20160622-autoValueJavacAnnotationsProcessing.copy.png)
 
 * Immutables  
![](../images/pages/compare_autovalue_lombok_immutables/20160622-immutablesJavacAnnotationsProcessing.png)

반면에 Lombok 의 경우 annotation 을 조금 다르게 사용하여 코드를 생성합니다.
Lombok은 템플릿 소스 코드와 동일한 클래스 이름으로 컴파일 된 .class 파일을 생성하고 생성 된 메서드를 컴파일 된 버전에 추가합니다.
버젼이 업그레이드 됨에 따라 delombok 셥을 통해 실제 생성된 파일 확인이 가능하지만 기본적으로 Lombok 은 기본 템플릿 코드만 가지고, 중간 소스 파일(lombok을 통해 생성된)을 거치지(신경쓰지) 않도록 설계되었습니다.

 * Lombok  
![](../images/pages/compare_autovalue_lombok_immutables/20160622-lombokJavacAnnotationsProcessing.png)

## 비교

| 특성 | Lombok | AutoValue | Immutables | 비고
|:---------------- | :------------ | :------------ | :----------- | :----------- 
|확인 버젼 | 1.16.8 (2016) | 1.2 (2016) | 2.2.8 (2016) | 포스팅에 쓰인 버젼
|개발 시작년도 | 2009 | 2014 | 2014 | 
|라이센스 | MIT (+@) | Apache 2 | 	Apache 2 | 모두 오픈소스
|자바 버젼 | 1.6+ | 1.6+ | 1.7+ | 
|Dependencies | ASM (for Eclipse integration) | ASM | (Optional) Runtime<br> Dependency: Guava | Libraries dependent<br> upon (included) at compile time
|템플릿 소스와 생성 소스의 관계 | 템플릿 소스를 교체 | 생성소스가 템플릿 소스를 extend | 생성소스가 템플릿 소스를 extend | 
|생성 소스 접근 | delombok 옵션시 가능 | Default | Default | 
|생성 메소드 | 기본 | 기본 setter + 제외 | 기본 | 
|Immutablity 정도 | 유연함 | 기본 컨셉에만 충실 | 중간 | Lombok < Immutables < AutoValue
|Features | Resource cleanup,<br> Immutable or Mutable,<br> Sneakily thrown checked exceptions,<br>  Object synchronization locks,<br>  Logging annotation, ... | Faithfulness to<br> Value Object concept | Style customization,<br> Serialization (including JSON),<br> Pre-computed hash codes, ... | 

*기본 메소드 : equals(Object), hashCode(), toString(), construction/builder, accessors, setters 

## 선택시 고려 사항
`Lombok`, `AutoValue` 및 `Immutables`의 장단점은 비슷합니다. 하지만 프로젝트나 개인의 스타일에 따라서 사용할 툴킷을 선택할 때 고려할만한 부분은 아래와 같습니다.

* `Lombok`은 템플릿과 동일한 패키지 및 클래스 이름을 가진 클래스를 생성하지만 AutoValue 및 `Immutables`는 템플릿 클래스를 확장하고 자체 클래스 이름 (동일한 패키지)을 갖는 클래스를 생성합니다.
  * 컴파일 된 .class 파일이 템플릿 클래스와 동일한 패키지 및 이름으로 사용하는것이 선호된다면 `Lombok`.
  * 소스 코드가 생성되는것을 선호하며, 템플릿 파일과 충돌이없는 생성 소스를 언제든지 사용할 수 있기를 바라면 AutoValue 또는 `Immutables`.

* `AutoValue`는 value object 컨셉에 충실했기에 `Lombok`과 달리 변경에 가장 자유롭지 못합니다.  
  * value object 의 특성을 엄격하게 적용하고자하는 개발자는 `AutoValue`를..
  `AutoValue`는 생성 되는 클래스를 수정할 수 있는 메커니즘을 제공하지 않습니다.
  템플릿에 추상 클래스로만 표현 될 수 있도록 허용하고 인터페이스로 사용할 수 없게 하는 등, 다른 두 툴킷이 시행하지 않는 몇 가지 다른 규칙을 시행합니다.
  * `Immutables` 는 템플릿에 interface를 허용합니다.
  * 템플릿 및 immutability 에 조금 더 자유로워 지고 싶다면 `Lombok`.
  
* `AutoValue` 및 `Immutables`는 표준 annotaion 처리를 사용하고 `Lombok` 는 그렇지 않습니다.
  * 비표준 dependency 피하려는 개발자, javac 외부의 IDE plugin/그외 툴 사용을 피하려는 개발자 는 `AutoValue` 또는 `Immutables`.

* `AutoValue`와 `Lombok`은 JDK 1.6에서 `Immutables`는 JDK 1.7이 필요합니다.    
   
  
    
      
      
참고 자료 
[DustinMarx's post (http://marxsoftware.blogspot.kr/2016/06/lombok-autovalue-immutables.html)](http://marxsoftware.blogspot.kr/2016/06/lombok-autovalue-immutables.html)

{% include note.html content="Lombok, AutoValue 및 Immutables는 공통점이 많으며 모두 간단한 템플릿 파일을 통해서 boiler plate 부분을 처리해 주어 개발자가 high level programming 에만 집중 할 수 있게 해줍니다. 프로젝트의 특성이나 개발자의 개인적인 성향에 따라 사용할 툴을 정하면 될 것 같습니다." %}


{% include links.html %}
