import React from 'react';
import '../css/Sceduleinterview.css';

const Modal = ({ isOpen, onClose, title, userDetails }) => {
    console.log(userDetails)
  return (
    isOpen && (
      <div className="modal-overlay" onClick={onClose}>
        <div className="modal-content" onClick={(e) => e.stopPropagation()}>
          <div className="modal-header">
            <h3>{title}</h3>
            <button className="close-button" onClick={onClose}>
              &times;
            </button>
          </div>
          <div className="modal-body">
              
            <p>Name:{userDetails.FirstName} {userDetails.LastName}</p>
            <p>Email:{userDetails.Email}</p>
            <p>Address:{userDetails.Address}</p>
            <p>Mobile:{userDetails.Mobile}</p>
            <p>Qualification:{userDetails.Qualification}</p>
            <p>Gender:{userDetails.Gender}</p>
            {/* Add more details as needed */}
          </div>
        </div>
      </div>
    )
  );
};

export default Modal;
