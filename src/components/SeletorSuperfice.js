import React from 'react';
import '../styles/SeletorSuperfice.css';

const SeletorSuperfice = ({ surfaceType, setSurfaceType, resetDimensions, multiplicidade, setMultiplicidade }) => {

    return (
        <div className="form-group">
            <div className="input-group">
                <label>Tipo Superfície:</label>
                <select
                    value={surfaceType}
                    onChange={(e) => {
                        setSurfaceType(e.target.value);
                        resetDimensions();
                    }}
                >
                    <option value="">Selecione</option>
                    <option value="cone">Cone</option>
                    <option value="circulo">Círculo</option>
                    <option value="quadrado">Quadrado</option>
                    <option value="retangulo">Retângulo</option>
                </select>
                <label>Fator de Multiplicidade:</label>
                <input
                    type="number"
                    value={multiplicidade}
                    onChange={(e) => setMultiplicidade(e.target.value)}
                    min="1"
                    style={{ marginLeft: '10px' }}
                />
            </div>
        </div>
    );
};

export default SeletorSuperfice;
