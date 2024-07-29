import { collection, addDoc, getDocs, updateDoc, deleteDoc, doc } from "firebase/firestore"; 
import { db } from "./FireBaseConf";


const addPaint = async (name, value) => {
    try {
        const docRef = await addDoc(collection(db, "tintas"), {
            name: name,
            value: value
        });
        console.log("Document written with ID: ", docRef.id);
    } catch (e) {
        console.error("Error adding document: ", e);
    }
};

const getPaints = async () => {
    const querySnapshot = await getDocs(collection(db, "tintas"));
    const paints = {};
    querySnapshot.forEach((doc) => {
        paints[doc.id] = doc.data();
    });
    return paints;
};

const updatePaint = async (id, name, value) => {
    const paintDoc = doc(db, "tintas", id);
    await updateDoc(paintDoc, {
        name: name,
        value: value
    });
};

const deletePaint = async (id) => {
    await deleteDoc(doc(db, "tintas", id));
};

export { addPaint, getPaints, updatePaint, deletePaint };