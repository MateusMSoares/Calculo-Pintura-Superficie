// components/CalcularResultado.js

import React, { useState } from 'react';
import SeletorSuperfice from './SeletorSuperfice';
import DimensionsInput from './InputDimensao';
import PaintTypeSelector from './SeletorTinta';
import GerenciadorTinta from './GerenciadorTinta';
import calculatePaintCost from './CalcularResultado';
import Modal from 'react-modal';
import '../styles/CalcularResultado.css';

const PaintCalculator = () => {
    const [surfaceType, setSurfaceType] = useState('');
    const [surfaceDimensions, setSurfaceDimensions] = useState({ dimensao1: '', dimensao2: '' });
    const [paintType, setPaintType] = useState('');
    const [paintTypes, setPaintTypes] = useState({});
    const [result, setResult] = useState(null);
    const [area, setArea] = useState(null);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [multiplicidade, setMultiplicidade] = useState(1);

    const handleCalculate = () => {
        const { result, area } = calculatePaintCost(surfaceType, surfaceDimensions, paintTypes, paintType, multiplicidade);
        setResult(result);
        setArea(area);
    };

    const resetDimensions = () => setSurfaceDimensions({ dimensao1: '', dimensao2: '' });

    return (
        <div className="container">
            <h1>Calculadora de Tinta</h1>
            <SeletorSuperfice 
            surfaceType={surfaceType} 
            setSurfaceType={setSurfaceType} 
            resetDimensions={resetDimensions}
            multiplicidade={multiplicidade}
            setMultiplicidade={setMultiplicidade} 
            />
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
                            <p>Custo total = {area} m² x R${paintTypes[paintType]?.value+". Multiplicidade: " + multiplicidade}</p>
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
