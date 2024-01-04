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