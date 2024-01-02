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

function App() {
  /*
    const _mode = useState("None");
    const mode = _mode[0]; // index 0 : 상태 값인 "None"을 담고 있음
    const setMode = _mode[1]; // index 1 : 상태 값을 변경할 때 사용하는 함수를 담고 있음
  */
  const [mode, setMode] = useState("None"); // 축약형
  const [id, setId] = useState(null);

  const topics = [
    {id:1, title:"HTML", body:"HTML is..."},
    {id:2, title:"CSS", body:"CSS is..."},
    {id:3, title:"JavaScript", body:"JavaScript is..."}
  ];

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
    </div>
  );
}

export default App;