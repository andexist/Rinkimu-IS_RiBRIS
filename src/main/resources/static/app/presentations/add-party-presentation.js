const React = require('react');

var AddPartyPresentation = React.createClass({
  render: function() {
    return (
      <div className="container-fluid">
        <div className="col-sm-offset-1 col-sm-10">
          <form>
            <h4>Registruoti naują apartijų</h4>
            <br />
            <div className="form-group">
                <label>Pavadinimas</label>
                <input id="pavadinimas" className="form-control" type="text" value={this.props.party.name}
                   onChange={this.props.onFieldChange('name')} />
                <br />
            </div>
            <div className="form-group">
                <label>Partijos numeris</label>
                <input id="adresas" className="form-control" type="number" value={this.props.party.partyNo}
                  onChange={this.props.onFieldChange('partyNo')} />
                <br />
            </div>
            <button className="btn btn-success btn-sm" style={{ marginRight: '20px' }} onClick={this.props.onSaveClick}>Registruoti</button>
            <button className="btn btn-danger btn-sm" style={{ marginRight: '20px' }} onClick={this.props.onCancelClick}>Atšaukti</button>
          </form>
        </div>
      </div>
    );
  }
});

// AddPartyPresentation.contextTypes = {
//     party: React.PropTypes.object.isRequired,
//     onFieldChange: React.PropTypes.func.isRequired,
//     onSaveClick: React.PropTypes.func.isRequired,
//     onCancelClick: React.PropTypes.func.isRequired
// };

module.exports = AddPartyPresentation;
