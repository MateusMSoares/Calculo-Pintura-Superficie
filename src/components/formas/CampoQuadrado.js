import React, { useState, useEffect } from 'react';

const CampoQuadrado = ({ calcularArea }) => {
    const [lado, setLado] = useState(0);

    useEffect(() => {
        const area = lado * lado;
        calcularArea(area);
    }, [lado, calcularArea]);

    return (
        <div>
            <label>Lado:</label>
            <input
                type="number"
                value={lado}
                onChange={(e) => setLado(parseFloat(e.target.value))}
            />
        </div>
    );
};

export default CampoQuadrado;
