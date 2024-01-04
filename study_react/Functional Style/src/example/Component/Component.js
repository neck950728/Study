function Header(){ // 주의 : 함수(컴포넌트)명 첫 글자는 반드시 대문자로 지정
  return <header>
    <h1><a href="/">Web</a></h1>
  </header>
}

function Nav(){
  return <nav>
    <ol>
      <li><a href="/read/1">HTML</a></li>
      <li><a href="/read/2">CSS</a></li>
      <li><a href="/read/3">JavaScript</a></li>
    </ol>
  </nav>
}

function Article(){
  return <article>
    <h2>Welcome!</h2>
    Hello, Web
  </article>
}

function App() {
  return (
    <div>
      <Header />
      <Nav />
      <Article />
    </div>
  );
}

export default App;