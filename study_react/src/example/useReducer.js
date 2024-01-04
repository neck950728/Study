import { useState, useReducer } from "react";

function App() {
  /*
    const [count, setCount] = useState(0);

    function down(){
      setCount(count - 1);
    }

    function reset(){
      setCount(0);
    }

    function up(){
      setCount(count + 1);
    }

    return (
      <div>
        <input type="button" value="-" onClick={down} />
        <input type="button" value="0" onClick={reset} />
        <input type="button" value="+" onClick={up} />
        <span>{count}</span>
      </div>
    );
  */

  function countReducer(oldCount, action){
    if(action === "DOWN"){
      return oldCount - 1;
    }else if(action === "RESET"){
      return 0;
    }else if(action === "UP"){
      return oldCount + 1;
    }
  }

  const [count, countDispatch] = useReducer(countReducer, 0);
  
  function down(){
    countDispatch("DOWN");
  }

  function reset(){
    countDispatch("RESET");
  }

  function up(){
    countDispatch("UP");
  }

  return (
    <div>
      <input type="button" value="-" onClick={down} />
      <input type="button" value="0" onClick={reset} />
      <input type="button" value="+" onClick={up} />
      <span>{count}</span>
    </div>
  );
}

export default App;