import React, { useState } from 'react';
import '../styles/SeletorSuperfice.css';
import CampoCone from './formas/CampoCone';
import CampoCirculo from './formas/CampoCirculo';
import CampoQuadrado from './formas/CampoQuadrado';
import CampoRetangulo from './formas/CampoRetangulo';

const SeletorSuperfice = ({ nomeFace, setNomeFace, surfaceType, setSurfaceType, multiplicidade, setMultiplicidade }) => {
    const [resultado, setResultado] = useState(0);
    const calcularArea = (area) => {
        setResultado(area * multiplicidade);
    };

    return (
        <div className="form-container">
            <div className="titulos">
                <label>Nome da Face:</label>
                <label>Tipo Superfície:</label>
                <label>Fator de Multiplicidade:</label>
                <label>Área:</label>
            </div>
            <div className="entradas">
                <input type="text" value={nomeFace} onChange={(e) => setNomeFace(e.target.value)} />
                <select value={surfaceType} onChange={(e) => setSurfaceType(e.target.value)}>
                    <option value="">Selecione</option>
                    <option value="cone">Cone</option>
                    <option value="circulo">Círculo</option>
                    <option value="quadrado">Quadrado</option>
                    <option value="retangulo">Retângulo</option>
                </select>
                <input
                    type="number"
                    value={multiplicidade}
                    onChange={(e) => setMultiplicidade(parseFloat(e.target.value))}
                    min="1"
                />
                <p>{resultado}</p>
            </div>
            <div className="entrada-calculo">
                {surfaceType === "cone" && <CampoCone calcularArea={calcularArea} />}
                {surfaceType === "circulo" && <CampoCirculo calcularArea={calcularArea} />}
                {surfaceType === "quadrado" && <CampoQuadrado calcularArea={calcularArea} />}
                {surfaceType === "retangulo" && <CampoRetangulo calcularArea={calcularArea} />}
            </div>
        </div>
    );
};

export default SeletorSuperfice;
