const React = require('react');

var AddRepresentativePresentation = React.createClass({
  render: function() {

  return (
    <form>
      <h4>Registruoti naują atstovą</h4><br />
      <div className="form-group">
        <label>Vardas</label>
        <input className="form-control" type="text" required value={this.props.name} onChange={this.props.onNameChange} />
        <br />
      </div>
      <div className="form-group">
        <label>Pavardė</label>
        <input className="form-control" type="text" required value={this.props.surname} onChange={this.props.onSurnameChange} />
        <br />
      </div>
      <div className="form-group">
        <label>Priskirti atstovui apylinkę</label>
        <input className="form-control" type="text" value={this.props.district.name} onChange={this.props.onDistrictChange} />
      </div>
      <button className="btn btn-success btn-sm" style={{ marginRight: '20px' }} onClick={this.props.onSaveClick}>Registruoti</button>
      <button className="btn btn-danger btn-sm" style={{ marginRight: '20px' }} onClick={this.props.onCancelClick}>Atšaukti</button>
    </form>
    );
  }
});

module.exports = AddRepresentativePresentation;