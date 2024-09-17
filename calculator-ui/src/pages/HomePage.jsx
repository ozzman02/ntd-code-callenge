import CalculatorComponent from '../components/CalculatorComponent';
import HeaderComponent from '../components/HeaderComponent';
import FooterComponent from '../components/FooterComponent';
import useAuthorizationContext from '../hooks/UseAuthorizationContext';

export default function HomePage() {

    const { getUser } = useAuthorizationContext();

    console.log("Home Page -> ", getUser().username);
    
    return (
        <div className='container-fluid padding-bottom:60px;'>
            <HeaderComponent username={getUser().username} />
            <CalculatorComponent />
            <FooterComponent />
        </div>
    );
}