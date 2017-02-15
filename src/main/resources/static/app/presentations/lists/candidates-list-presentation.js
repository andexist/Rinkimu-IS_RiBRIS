const React = require('react');

var CandidatesList = React.createClass({
  render: function() {
    var self = this;
    var nr = 1;
    var CandidatesList = this.props.candidates.map(function(candidate, index) {
      return (
        <tr key={index}>
          <td>{nr++}</td>
          <td>{candidate.name}</td>
          <td>{candidate.surname}</td>
          <td>{candidate.birthDate}</td>
          <td>{candidate.partyName}</td>
          <td>
            <button className="btn btn-success btn-sm" onClick={self.props.onEditItem(candidate)}>
              <i className="fa fa-pencil" aria-hidden="true"></i>
            </button>
            <button className="btn btn-danger btn-sm" onClick={self.props.onRemoveItem(candidate)}>
              <i className="fa fa-times" aria-hidden="true"></i>
            </button>
          </td>
        </tr>
      );
    });

    return (
      <div className="container-fluid">
        <div className="panel panel-default">
        <div className="panel-heading"><strong>Kandidatų sąrašas</strong></div>
          <table className="table">
            <thead>
              <tr>
                <th>Nr</th>
                <th>Vardas</th>
                <th>Pavardė</th>
                <th>Gimimo data</th>
                <th>Partija</th>
                <th>Redaguoti</th>
              </tr>
            </thead>
            <tbody>
              {CandidatesList}
            </tbody>
          </table>
        </div>
      </div>
    )
  }
});

/*CandidatesList.propTypes = {
  parties: React.PropTypes.array.isRequired,
  onCandidatesListClick: React.PropTypes.func.isRequired,
  onRemoveItem: React.PropTypes.func.isRequired,
  onEditItem: React.PropTypes.func.isRequired
};*/

module.exports = CandidatesList;