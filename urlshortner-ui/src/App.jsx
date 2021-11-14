import logo from './logo.svg';
import './App.css';
import URLShortnerLayout from "./components/URLShortnerLayout";
import {Header} from "./components/Header";

function App() {
  return (
    <div className="background">
        <Header></Header>
        <div className="App-logo">
            <URLShortnerLayout> </URLShortnerLayout>
        </div>
    </div>
  );
}

export default App;
