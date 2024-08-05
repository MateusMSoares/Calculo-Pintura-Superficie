import { collection, addDoc, getDocs, updateDoc, deleteDoc, doc } from "firebase/firestore"; 
import { db } from "./FireBaseConf";
import Equipamento from "../entitys/Equipamento";

const addEquipamento = async (Equipamento) => {
    try {
        const { tag, atributos, tagUnidade } = Equipamento;
        const docRef = await addDoc(collection(db, "equipamentos"), {
            tag: tag,
            atributos: atributos,
            tagUnidade: tagUnidade
        });
        console.log("Document written with ID: ", docRef.id);
    } catch (e) {
        console.error("Error adding document: ", e);
    }
};

const getEquipamentos = async () => {
    const querySnapshot = await getDocs(collection(db, "equipamentos"));
    const equipamentos = {};
    querySnapshot.forEach((doc) => {
        equipamentos[doc.id] = doc.data();
    });
    return equipamentos;
};

const updateEquipamento = async (id, tag, nome) => {
    const paintDoc = doc(db, "equipamentos", id);
    await updateDoc(paintDoc, {
        tag: tag,
        value: value
    });
};

const deletePaint = async (id) => {
    await deleteDoc(doc(db, "tintas", id));
};

export { addPaint, getPaints, updatePaint, deletePaint };