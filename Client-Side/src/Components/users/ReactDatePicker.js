import { useState } from 'react';
import DatePicker from 'react-datepicker'
import "react-datepicker/dist/react-datepicker.css"

function ReactDatePicker() {

    const [selectedDate,setSelectedDate] = useState(null)
    const maxDate = new Date("2024-12")
    
    const handledatechange=(date)=>
    {
        setSelectedDate(date);
    }

    return ( 
        <>
            <p>
                hello this is my name
            </p>
            <DatePicker
            
            selected={selectedDate}
            onChange={handledatechange}
            dateFormat="MMMM d, yyyy h:mm aa"
            minDate={(new Date())}
           maxDate={maxDate}
            showTimeSelect
            timeIntervals={30}
            timeFormat='hh:mm'
            
            />
        </>
     );
}

export default ReactDatePicker;