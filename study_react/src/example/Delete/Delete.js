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

function Update(props){
  const [title, setTitle] = useState(props.title);
  const [body, setBody] = useState(props.body);

  return <article>
    <h2>Update</h2>
    <form onSubmit={event=>{
      event.preventDefault();
      const title = event.target.title.value;
      const body = event.target.body.value;
      props.onUpdate(title, body);
    }}>
      <p><input type="text" name="title" value={title} placeholder="title" onChange={event=>{
        setTitle(event.target.value);
      }} /></p>
      <p><textarea name="body" value={body} placeholder="body" onChange={event=>{
        setBody(event.target.value);
      }} /></p>
      <p><input type="submit" value="Update" /></p>
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
  let contextControl = null;
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

    contextControl = <>
      <li><a href={"/update/" + id} onClick={event=>{
        event.preventDefault();
        setMode("Update");
      }}>Update</a></li>
      <li><input type="button" value="Delete" onClick={()=>{
        const clonedTopics = [...topics];
        for(let i = 0; i < clonedTopics.length; i++){
          if(clonedTopics[i].id === id){
            clonedTopics.splice(i, 1);
            break;
          }
        }
        
        setTopics(clonedTopics);
        setMode("Welcome");
      }} /></li>
    </>
  }else if(mode === "Create"){
    content = <Create onCreate={(title, body)=>{
      const clonedTopics = [...topics];
      clonedTopics.push({id:nextId, title:title, body:body});
      setTopics(clonedTopics);
      setMode("Read");
      setId(nextId);
      setNextId(nextId + 1);
    }} />
  }else if(mode === "Update"){
    let title, body = null;
    for(const topic of topics){
      if(topic.id === id){
        title = topic.title;
        body = topic.body;
        break;
      }
    }

    content = <Update title={title} body={body} onUpdate={(title, body)=>{
      const clonedTopics = [...topics];
      for(let i = 0; i < clonedTopics.length; i++){
        if(clonedTopics[i].id === id){
          clonedTopics[i].title = title;
          clonedTopics[i].body = body;
          break;
        }
      }

      setTopics(clonedTopics);
      setMode("Read");
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
      
      <ul>
        <li>
          <a href="/create" onClick={event=>{
            event.preventDefault();
            setMode("Create");
          }}>Create</a>
        </li>
        {contextControl}
      </ul>
    </div>
  );
}

export default App;