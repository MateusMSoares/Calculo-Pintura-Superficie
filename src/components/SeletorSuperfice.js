import React from 'react';

const SeletorSuperfice = ({ surfaceType, setSurfaceType, resetDimensions }) => (
    <div className="form-group">
        <label>Tipo Superfície:</label>
        <select value={surfaceType} onChange={(e) => {
            setSurfaceType(e.target.value);
            resetDimensions();
        }}>
            <option value="">Selecione</option>
            <option value="cone">Cone</option>
            <option value="circulo">Círculo</option>
            <option value="quadrado">Quadrado</option>
        </select>
    </div>
);

export default SeletorSuperfice;
