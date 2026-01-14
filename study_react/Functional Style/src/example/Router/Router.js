import '../index.css';
import { Route, Routes, Link, NavLink, useParams } from 'react-router-dom';

function Home(){
  return(
    <div>
      <h2>Home</h2>
      Home...
    </div>
  )
}

var contents = [
  {id:1, title:"HTML", description:"HTML is..."},
  {id:2, title:"JavaScript", description:"JavaScript is..."},
  {id:3, title:"React", description:"React is..."}
];

function Topic(){
// function Topic(props){
  // var topicId = props.match.params.topicId;

  /*
    ex) <Route path="/topics/:topicId" component={Topic} />
    React Router v5까지는 Route가 컴포넌트를 생성할 때 내부적으로 라우터 관련 객체를 props에 주입해 줬었다.
    그리고 props를 통해 props.match / props.history / props.location과 같이 꺼내 사용할 수 있었다.
    하지만 v6부터는 더 이상 주입해 주지 않는다고 한다.
    그 이유는 설계 철학이 다음과 바뀌었기 때문이다.

    ✨ v6 설계 철학 ✨
    - 컴포넌트는 라우터에 의존하지 않아야 한다.
    - 암묵적으로 주입되던 라우터 props를 제거하고, 필요한 정보는 Hook을 통해 명시적으로 가져오도록 한다.
    - API를 Hook 중심으로 통일하여 사용 패턴을 단순화한다.
    - 컴포넌트의 재사용성과 테스트 용이성을 높인다.
    - TypeScript 친화적인 구조를 제공한다.

    ❓ 그럼, 이제 어떻게 사용해야 하지
    - props.match.params  →  useParams()
    - props.history  →  useNavigate()
    - props.location  →  useLocation()
    ※props.match : https://naver.me/5R4uuI5o 참고
  */

  var params = useParams();
  console.log(params);

  var selectedTopic = {
    title:"Sorry",
    description:"Not Found"
  };

  for(var content of contents){
    if(content.id === Number(params.topicId)){
      selectedTopic = content;
      break;
    }
  }

  return(
    <div>
      <h3>{selectedTopic.title}</h3>
      {selectedTopic.description}
    </div>
  )
}

function Topics(){
  var list = [];
  for(var content of contents){
    list.push(<li key={content.id}><NavLink to={"/topics/" + content.id}>{content.title}</NavLink></li>);
  }

  return(
    <div>
      <h2>Topics</h2>
      <ul>
        {list}
      </ul>
      {/* 
        https://blog.naver.com/dngu_icdi/223311667738 : '중첩 라우팅' 2번 참고
        <Routes>
          <Route path="1" element="HTML is..." />
          <Route path="2" element="JavaScript is..." />
          <Route path="3" element="React is..." />
        </Routes>
      */}
      <Routes>
        {/* 경로 파라미터를 이용한 동적 라우팅 */}
        <Route path=":topicId" element={<Topic />} />
      </Routes>
    </div>
  )
}

function Contact(){
  return(
    <div>
      <h2>Contact</h2>
      Contact...
    </div>
  )
}

function App(){
  return(
    <div>
      <h1>React Router DOM!</h1>
      <ul>
        {/*
          <li><a href="/">Home</a></li>
          <li><a href="/topics">Topics</a></li>
          <li><a href="/contact">Contact</a></li>
        */}
        {/*
          -Link : 해당 경로로 이동 시 페이지가 리로드되지 않음
          -NavLink : 해당 경로로 이동 시 페이지가 리로드되지 않음 + 자동으로 'active'라는 클래스가 부여됨
        */}
        <li>
          <Link to="/">Home</Link> &nbsp;
          <NavLink to="/">Home</NavLink>
        </li>
        <li>
          <Link to="/topics">Topics</Link> &nbsp;
          <NavLink to="/topics">Topics</NavLink>
        </li>
        <li>
          <Link to="/contact">Contact</Link> &nbsp;
          <NavLink to="/contact">Contact</NavLink>
        </li>
      </ul>
      {/*
        <Route path="/"><Home /></Route>
        <Route path="/topics"><Topics /></Route>
        <Route path="/contact"><Contact /></Route>
        
        v6로 업그레이드되면서 문법이 아래와 같이 변경됨
      */}
      <Routes>
        {/*
          v6로 업그레이드되면서 기본적으로 exact가 적용되어, 더 이상 exact는 사용하지 않는다고 한다.
          <Route exact path="/"><Home /></Route>
        */}
        <Route path="/" element={<Home />} />
        <Route path="/topics/*" element={<Topics />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="*" element="404 Not Found!" />
      </Routes>
    </div>
  )
}

export default App;