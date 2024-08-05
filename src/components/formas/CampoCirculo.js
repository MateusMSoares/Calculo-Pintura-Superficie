import React, { useState, useEffect } from 'react';

const CampoCirculo = ({ calcularArea }) => {
    const [raio, setRaio] = useState(0);

    useEffect(() => {
        const area = Math.PI * raio * raio;
        calcularArea(area);
    }, [raio, calcularArea]);

    return (
        <div>
            <label>Raio:</label>
            <input
                type="number"
                value={raio}
                onChange={(e) => setRaio(parseFloat(e.target.value))}
            />
        </div>
    );
};

export default CampoCirculo;
