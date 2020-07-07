import React from 'react';
import ReactDOM  from "react-dom"; 
import store from "./js/store/index";
//import './index.scss'; 
import NavigationComponent from './js/components/NavigationComponent';
import { getOneCar} from './js/actions';
import MainComponent from './js/components/MainComponent';
import { Provider } from 'react-redux';

const wrapper = document.getElementById("container");

const mainBlock = <React.StrictMode>
    <NavigationComponent />
</React.StrictMode>;
const reduxMainBlock = <Provider store={store}>
                            <MainComponent />
                        </Provider>

window.store = store;
window.getOneCar = getOneCar; 

wrapper? ReactDOM.render(
    reduxMainBlock   
    , 
    wrapper): false;
 
