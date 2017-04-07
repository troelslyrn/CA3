import React from 'react';
import ReactDOM from 'react-dom';
import './styles/index.css';
import {hashHistory, Router, Route, IndexRoute} from 'react-router'
import App from './pages/App';
import Home from './pages/Home';
import Login from "./pages/Login";
import Logout from "./pages/Logout";
import About from "./pages/About";
import Documentation from "./pages/Documentation";
import Product from "./pages/Product";
import UserPage from "./pages/UserPage";
import AdminPage from "./pages/AdminPage";
import Dashboard from "./pages/Dashboard"
import bookStore from "./stores/bookStore";
import Details from "./pages/Details";
import Company from "./pages/Company";
import auth from "./authorization/auth";
import NewBook from  "./pages/NewBook"
import NewUser from "./pages/NewUser";

function requireAuth(nextState, replace) {
    if (!auth.loggedIn) {
        replace({
            pathname: '/login',
            state: {nextPathname: nextState.location.pathname}
        })
    }
}
ReactDOM.render((
    <Router history={hashHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={Home}/>
            <Route path="login" component={Login}/>
            <Route path="logout" component={Logout}/>
            <Route path="home" component={Home}/>
            <Route path="documentation" component={Documentation}/>
            <Route path="products" component={Product} bookStore={bookStore}/>
            <Route path="products/details/:id" component={Details}
                   books={bookStore.books}/>
            <Route path="company" component={Company}/>
            <Route path="books" component={About}/>
            <Route path="users" component={AdminPage}/>
            <Route path="dashboard" component={Dashboard} onEnter={requireAuth}/>
            <Route path="products/new-book" component={NewBook} />
            <Route path="products/newUser" component={NewUser} />
        </Route>
    </Router>
), document.getElementById('root'))