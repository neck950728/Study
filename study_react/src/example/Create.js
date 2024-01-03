import { useState } from "react";

function Header(props){
  return <header>
    <h1><a href="/" onClick={event=>{
      event.preventDefault();
      props.onChangeMode();
    }}>{props.title}</a></h1>
  </header>
}

function Nav(props){
  const list = [];
  for(const topic of props.topics){
    list.push(<li key={topic.id}>
      <a id={topic.id} href={"/read/" + topic.id} onClick={event=>{
        event.preventDefault();
        props.onChangeMode(Number(event.target.id));
      }}>{topic.title}</a>
    </li>);
  }

  return <nav>
    <ol>
      {list}
    </ol>
  </nav>
}

function Article(props){
  return <article>
    <h2>{props.title}</h2>
    {props.body}
  </article>
}

function Create(props){
  return <article>
    <h2>Create</h2>
    <form onSubmit={event=>{
      event.preventDefault();
      const title = event.target.title.value;
      const body = event.target.body.value;
      props.onCreate(title, body);
    }}>
      <p><input type="text" name="title" placeholder="title" /></p>
      <p><textarea name="body" placeholder="body" /></p>
      <p><input type="submit" value="Create" /></p>
    </form>
  </article>
}

function App() {
  const [mode, setMode] = useState("None");
  const [id, setId] = useState(null);
  const [nextId, setNextId] = useState(4);
  const [topics, setTopics] = useState([
    {id:1, title:"HTML", body:"HTML is..."},
    {id:2, title:"CSS", body:"CSS is..."},
    {id:3, title:"JavaScript", body:"JavaScript is..."}
  ]);

  let content = null;
  if(mode === "None"){
    content = <Article title="None" body="Hello" />;
  }else if(mode === "Welcome"){
    content = <Article title="Welcome" body="Hello, Welcome" />;
  }else if(mode === "Read"){
    for(const topic of topics){
      if(topic.id === id){
        content = <Article title={topic.title} body={topic.body} />;
        break;
      }
    }
  }else if(mode === "Create"){
    content = <Create onCreate={(title, body)=>{
      // https://www.youtube.com/watch?v=kctNCMFxciQ&list=PLuHgQVnccGMCOGstdDZvH41x0Vtvwyxu7&index=8 : 12분 45초부터 참고
      const clonedTopics = [...topics];
      clonedTopics.push({id:nextId, title:title, body:body});

      /*
        상태값이 변경되면, 즉 useState의 set... 함수를 통해 값을 변경하면 해당 컴포넌트는 리렌더링된다고 배웠다.
        (현재의 경우, 아래에서 App 컴포넌트의 상태값을 변경하고 있으므로 App 컴포넌트가 재실행, 즉 리렌더링된다.)
        이때 궁금한 것이 아래에서 상태값을 총 4번 변경하고 있으니, 그럼 이 경우 리렌더링이 4번 발생하는 건가?
        만약 그렇다면 성능적인 측면에서 봤을 때 굉장히 큰 단점 아닌가?
        
        https://blog.naver.com/dngu_icdi/223310698714 참고
        아직 지식이 부족하여 완벽히 이해하지는 못했지만,
        무튼 요약하자면 그룹화해서 한 번에 처리하기 때문에 리렌더링은 1번만 발생한다는 내용인 것 같다.
      */
      setTopics(clonedTopics);
      setMode("Read");
      setId(nextId);
      setNextId(nextId + 1);
    }} />
  }

  return (
    <div>
      <Header title="Web" onChangeMode={()=>{
        setMode("Welcome");
      }} />
      <Nav topics={topics} onChangeMode={id=>{
        setMode("Read");
        setId(id);
      }} />

      {content}
      
      <a href="/create" onClick={event=>{
        event.preventDefault();
        setMode("Create");
      }}>Create</a>
    </div>
  );
}

export default App;