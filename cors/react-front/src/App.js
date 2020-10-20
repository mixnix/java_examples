import React, {useEffect} from 'react';

const App = () => {
    useEffect(()=>{
        fetch("http://localhost:8080/api/cars")
            .then(response=>response.json)
            .then(data => {
                console.log(data);
            })
    }, [])
  return (
    <div className="App">
      Hello world
    </div>
  );
}

export default App;
