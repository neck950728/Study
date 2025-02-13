Auto Configuration은 주로 라이브러리를 만들어서 제공할 때 사용(사용자의 편의성을 위해)하고, 그 외에는 사실 거의 사용할 일이 없다.
왜냐하면 굳이 번거롭게 Auto Configuration을 사용할 필요 없이 Component Scan하거나 직접 등록하면 그만이기 때문이다.
여기서 "그럼 난 라이브러리 개발자가 아니니깐, 굳이 배울 필요 없는 거 아닌가?"라는 생각이 들 수도 있겠지만,
이와 관련된 문제를 만나게 되었을 때 분석은 할 수 있어야 하므로 모르는 것보단 알아두는 편이 좋다.
그러므로 라이브러리를 제공하는 입장이 되어, Auto Configuration을 직접 다뤄보며 좀 더 자세히 알아보도록 하자.
(아래 memory-v*는 라이브러리를 만드는 프로젝트이고, project-v*는 만든 라이브러리를 가져다 사용하는 프로젝트이다.)

- Auto Configuration을 사용하지 않은 버전 -
ㆍmemory-v1
ㆍproject-v1

- Auto Configuration을 사용한 버전 -
ㆍmemory-v2
ㆍproject-v2