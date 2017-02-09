const React = require('react');
const ReactRouter = require('react-router');
const Router = ReactRouter.Router;
const Route = ReactRouter.Route;
const IndexRoute = ReactRouter.IndexRoute;
const hashHistory = ReactRouter.hashHistory;
const NavBar = require('../presentations/navigation/navbar-component');
const NavListContainer = require('../containers/navigation/nav-cards-list-container');

const AddConstituency = require('../containers/add-constituency-container');
const AddDistrict = require('../containers/add-district-container');

const ConstituenciesList = require('../containers/lists/constituencies-list-container');
const DistrictsList = require('../containers/lists/districts-list-container');

const routes = (
  <Router history={hashHistory}>
    <Route path="/" component={NavBar}>
      <IndexRoute component={NavListContainer} />
      <Route path="/constituencies" component={NavListContainer} />
        <Route path="/constituencies/list" component={ConstituenciesList} />
        <Route path="/constituencies/add" component={AddConstituency} />
        <Route path="/constituencies/add-list" component={AddConstituency} />

      <Route path="/districts" component={DistrictsList} />
        <Route path="/districts/add" component={AddDistrict} />
        <Route path="/districts/add/:constituencyId" component={AddDistrict} />

      <Route path="/parties" component={NavListContainer} />
        <Route path="/parties/add" component={AddConstituency} />
        <Route path="/parties/add-list" component={AddConstituency} />

      <Route path="/candidates" component={NavListContainer} />
        <Route path="/candidates/add" component={AddConstituency} />
        <Route path="/candidates/add-list" component={AddConstituency} />

      <Route path="/results" component={NavListContainer} />
        <Route path="/results/add-list" component={AddConstituency} />
        <Route path="/results/edit" component={AddConstituency} />
        <Route path="/results/delete" component={AddConstituency} />
    </Route>
  </Router>
);

module.exports = routes;