import React, { useState, useEffect } from 'react';

const CampoCone = ({ calcularArea }) => {
    const [raio, setRaio] = useState(0);
    const [altura, setAltura] = useState(0);

    useEffect(() => {
        const area = Math.PI * raio * (raio + Math.sqrt(altura * altura + raio * raio));
        calcularArea(area);
    }, [raio, altura, calcularArea]);

    return (
        <div>
            <label>Raio:</label>
            <input
                type="number"
                value={raio}
                onChange={(e) => setRaio(parseFloat(e.target.value))}
            />
            <label>Altura:</label>
            <input
                type="number"
                value={altura}
                onChange={(e) => setAltura(parseFloat(e.target.value))}
            />
        </div>
    );
};

export default CampoCone;
