function Header(props){
  return <header>
    <h1><a href="/">{props.title}</a></h1>
  </header>
}

function Nav(props){
  const list = [];
  for(const topic of props.topics){
    /*
      - https://www.youtube.com/watch?v=t9e3hMJ_s-c&list=PLuHgQVnccGMCOGstdDZvH41x0Vtvwyxu7&index=5 : 9분 31초 ~ 11분 19초 참고
      - https://drive.google.com/file/d/1zUmbmY_ZIlKwhvWDFG0YUw1NNmT8wNhG 참고
    */
    list.push(<li key={topic.id}><a href={"/read/" + topic.id}>{topic.title}</a></li>);
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
      <Header title="Web" />
      <Nav topics={topics} />
      <Article title="Welcome!" body="Hello, Web" />
    </div>
  );
}

export default App;