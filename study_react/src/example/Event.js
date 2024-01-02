function Header(props){
  return <header>
    <h1><a href="/" onClick={event=>{ // 화살표 함수 : 파라미터가 하나일 땐 소괄호 생략 가능
      event.preventDefault(); // a 태그 기본 동작 방지
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
        props.onChangeMode(event.target.id);
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
  const topics = [
    {id:1, title:"HTML", body:"HTML is..."},
    {id:2, title:"CSS", body:"CSS is..."},
    {id:3, title:"JavaScript", body:"JavaScript is..."}
  ];

  return (
    <div>
      <Header title="Web" onChangeMode={()=>{
        alert("Header");
      }} />
      <Nav topics={topics} onChangeMode={id=>{
        alert(id);
      }} />
      <Article title="Welcome!" body="Hello, Web" />
    </div>
  );
}

export default App;