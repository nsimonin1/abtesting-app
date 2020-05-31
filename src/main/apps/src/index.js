import React from 'react';
import ReactDOM  from "react-dom"; 
//import './index.scss'; 
import NavigationComponent from './js/components/NavigationComponent';

const wrapper = document.getElementById("container");

wrapper? ReactDOM.render(
    <React.StrictMode>
        <NavigationComponent />
    </React.StrictMode>
    , 
    wrapper): false;
 
