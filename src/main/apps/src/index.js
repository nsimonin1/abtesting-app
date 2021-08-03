import React from 'react';
import ReactDOM  from "react-dom"; 
//import './index.scss';
import NavigationComponent from './js/components/NavigationComponent';
import RouteComponent from './js/components/RouteComponent';

const wrapper = document.getElementById("container");

wrapper? ReactDOM.render(
    /*<React.StrictMode>
        <NavigationComponent />
    </React.StrictMode>*/
    <React.StrictMode>
        <RouteComponent />
    </React.StrictMode>
    ,
    wrapper): false;


