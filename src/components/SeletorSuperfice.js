import React, { useState } from 'react';
import '../styles/SeletorSuperfice.css';
import CampoCone from './formas/CampoCone';
import CampoCirculo from './formas/CampoCirculo';
import CampoQuadrado from './formas/CampoQuadrado';
import CampoRetangulo from './formas/CampoRetangulo';

const SeletorSuperfice = ({ surfaceType, setSurfaceType, multiplicidade, setMultiplicidade }) => {
    const [resultado, setResultado] = useState(0);
    const calcularArea = (area) => {
        setResultado(area * multiplicidade);
    };
    
    return (
        <div className="form-container">
            <div className="form-row">
                <div className="form-column">
                    <label>Tipo Superfície:</label>
                </div>
                <div className="form-column">
                    <label>Fator de Multiplicidade:</label>
                </div>
            </div>
            <div className="form-row">
                <div className="form-column">
                    <select
                        value={surfaceType}
                        onChange={(e) => setSurfaceType(e.target.value)}
                    >
                        <option value="">Selecione</option>
                        <option value="cone">Cone</option>
                        <option value="circulo">Círculo</option>
                        <option value="quadrado">Quadrado</option>
                        <option value="retangulo">Retângulo</option>
                    </select>
                </div>
                <div className="form-column">
                    <input
                        type="number"
                        value={multiplicidade}
                        onChange={(e) => setMultiplicidade(parseFloat(e.target.value))}
                        min="1"
                    />
                </div>
            </div>
            {surfaceType === "cone" && <CampoCone calcularArea={calcularArea} />}
                {surfaceType === "circulo" && <CampoCirculo calcularArea={calcularArea} />}
                {surfaceType === "quadrado" && <CampoQuadrado calcularArea={calcularArea} />}
                {surfaceType === "retangulo" && <CampoRetangulo calcularArea={calcularArea} />}
            <label>Resultado da Área:</label>
            <p>{resultado}</p>
        </div>
    );
};

export default SeletorSuperfice;
