import CalculatorComponent from '../components/CalculatorComponent';
import HeaderComponent from '../components/HeaderComponent';
import FooterComponent from '../components/FooterComponent';

export default function HomePage() {
    return (
        <div className='container-fluid padding-bottom:60px;'>
            <HeaderComponent />
            <CalculatorComponent />
            <FooterComponent />
        </div>
    );
}