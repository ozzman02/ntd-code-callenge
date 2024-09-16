export default function HeaderComponent() {
    return (
      <div className="header">
        <a href="#default" className="logo">NTD Software Calculator</a>
        <div className="header-right">
          <a className="active" href="#home">Home</a>
          <a href="#instructions">Instructions</a>
          <a href="#about"><i>oscar.santamaria@ntdsoftware.com</i></a>
          <a href="#logout"><b>Logout</b></a>
        </div>
      </div>
    );
}