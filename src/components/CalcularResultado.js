import React, { useState } from 'react';
import SeletorSuperfice from './SeletorSuperfice';
import DimensionsInput from './InputDimensao';
import PaintTypeSelector from './SeletorTinta';
import GerenciadorTinta from './GerenciadorTinta';
import Modal from 'react-modal';
import '../styles/CalcularResultado.css';

const PaintCalculator = () => {
    const [surfaceType, setSurfaceType] = useState('');
    const [surfaceDimensions, setSurfaceDimensions] = useState({ dimensao1: '', dimensao2: '' });
    const [paintType, setPaintType] = useState(''); // Inicialize como string vazia
    const [paintTypes, setPaintTypes] = useState({});
    const [result, setResult] = useState(null);
    const [area, setArea] = useState(null); // Novo estado para armazenar a área calculada
    const [modalIsOpen, setModalIsOpen] = useState(false);

    const handleCalculate = () => {
        const dim1 = parseFloat(surfaceDimensions.dimensao1);
        const dim2 = parseFloat(surfaceDimensions.dimensao2);
        const selectedPaint = paintTypes[paintType];

        if (!selectedPaint) {
            console.error("Tinta selecionada não encontrada.");
            setResult('Tipo de tinta inválido');
            return;
        }

        const paintValue = selectedPaint.value;

        if (isNaN(dim1) || (surfaceType === 'cone' && isNaN(dim2))) {
            setResult('Dimensões inválidas');
            return;
        }

        let calculatedArea = 0;
        switch (surfaceType) {
            case 'cone':
                calculatedArea = Math.PI * dim1 * dim2;
                break;
            case 'circulo':
                calculatedArea = Math.PI * Math.pow(dim1, 2);
                break;
            case 'quadrado':
                calculatedArea = Math.pow(dim1, 2);
                break;
            default:
                setResult('Tipo de superfície inválido');
                return;
        }

        const totalCost = calculatedArea * paintValue;
        setArea(calculatedArea.toFixed(2)); // Atualize o estado da área
        setResult(totalCost.toFixed(2));
    };

    const resetDimensions = () => setSurfaceDimensions({ dimensao1: '', dimensao2: '' });

    return (
        <div className="container">
            <h1>Calculadora de Tinta</h1>
            <SeletorSuperfice surfaceType={surfaceType} setSurfaceType={setSurfaceType} resetDimensions={resetDimensions} />
            {surfaceType && (
                <DimensionsInput surfaceType={surfaceType} surfaceDimensions={surfaceDimensions} setSurfaceDimensions={setSurfaceDimensions} />
            )}
            <PaintTypeSelector 
                paintType={paintType} 
                setPaintType={setPaintType} 
                setPaintTypes={setPaintTypes} 
                openPaintManager={() => setModalIsOpen(true)} 
            />
            <button onClick={handleCalculate}>Calcular</button>
            {result && (
                <div className="result">
                    <h2>Resultado Final: R${result}</h2>
                    {area !== null && (
                        <div>
                            <p>Custo total = {area} m² x R${paintTypes[paintType]?.value}</p>
                        </div>
                    )}
                </div>
            )}
            <Modal isOpen={modalIsOpen} onRequestClose={() => setModalIsOpen(false)} contentLabel="Gerenciar Tintas">
                <GerenciadorTinta paintTypes={paintTypes} setPaintTypes={setPaintTypes} closeModal={() => setModalIsOpen(false)} />
            </Modal>
        </div>
    );
};

export default PaintCalculator;
