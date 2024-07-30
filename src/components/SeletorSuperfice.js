import React from 'react';
import '../styles/SeletorSuperfice.css';

const SeletorSuperfice = ({ surfaceType, setSurfaceType, resetDimensions, multiplicidade, setMultiplicidade }) => {
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
        </div>
    );
};

export default SeletorSuperfice;
