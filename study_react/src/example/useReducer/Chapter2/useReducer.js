import { useState, useReducer } from "react";

function App() {
  function countReducer(oldCount, action){
    if(action.type === "DOWN"){
      return oldCount - action.number;
    }else if(action.type === "RESET"){
      return 0;
    }else if(action.type === "UP"){
      return oldCount + action.number;
    }
  }

  const [count, countDispatch] = useReducer(countReducer, 0);
  const [number, setNumber] = useState(1);
  
  function down(){
    countDispatch({type:"DOWN", number:number});
  }

  function reset(){
    countDispatch({type:"RESET", number:number});
  }

  function up(){
    countDispatch({type:"UP", number:number});
  }

  function changeNumber(event){
    setNumber(Number(event.target.value));
  }

  return (
    <div>
      <input type="button" value="-" onClick={down} />
      <input type="button" value="0" onClick={reset} />
      <input type="button" value="+" onClick={up} />
      <input type="text" value={number} onChange={changeNumber} />
      <span>{count}</span>
    </div>
  );
}

export default App;