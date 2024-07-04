import './App.css'
import { useEffect, useState } from "react"
import Form from './components/Form'
import GuestList from './components/GuestList'
import Button from './components/Button'

const _api = "http://localhost:8080/api/guests"

function App() {
  const [acceptEmail, setAcceptEmail] = useState(false)
  const [guests, setGuests] = useState([])
  const [email, setEmail] = useState("")
  const [fullName, setFullName] = useState("")
  const [isCheckingIn, setIsCheckingIn] = useState(false)
  const [phoneNumber, setPhoneNumber] = useState("")

  // main functions
  const addGuest = async (newGuestData) => {
    await fetch(_api, {
      body: JSON.stringify(newGuestData),
      headers: {
        "Content-Type": "application/json"
      },
      method: "POST",
    })

    handleIsCheckingIn()
    handleGetGuests()
    handleSetDefaultState()
  }

  const getGuests = async () => {
    const response = await fetch("http://localhost:8080/api/guests")
    const data = await response.json()

    setGuests(data)
  }
  // handle functions
  const handleAcceptEmail = () => {
    setAcceptEmail((prev) => !prev)
  }

  const handleAddGuest = () => {
    const newGuestData = {
      email: email,
      fullName: fullName,
      phoneNumber: phoneNumber,
    }

    addGuest(newGuestData)
  }

  const handleGetGuests = () => {
    getGuests()
  }

  const handleIsCheckingIn = () => {
    setIsCheckingIn((prev) => !prev)
  }

  const handleSetDefaultState = () => {
    setAcceptEmail(false)
    setEmail("")
    setFullName("")
    setPhoneNumber("")
  }

  const handleSetEmail = (event) => {
    const value = event.target.value

    setEmail(value)
  }

  const handleSetFullName = (event) => {
    const value = event.target.value

    setFullName(value)
  }

  const handleSetPhoneNumber = (event) => {
    const value = event.target.value

    setPhoneNumber(value)
  }

  const handleCheckIn = (event) => {
    handleIsCheckingIn()

    event.preventDefault()

    handleAddGuest()
  }

  useEffect(() => {
    handleGetGuests()
  }, [])

  return (
    <div className="flex flex-col gap-3 items-center justify-center max-h-screen min-h-screen p-10 w-screen">
      <h1 className='text-2xl'>Welcome to check in system!</h1>
      <p>This app is used to check in and save your spot to an imaginary company that does not exist! <span className='italic'>Think of checking in when getting a hair cut.</span></p>

      <Form
        acceptEmail={acceptEmail}
        email={email}
        fullName={fullName}
        isCheckingIn={isCheckingIn}
        handleAcceptEmail={handleAcceptEmail}
        handleCheckIn={handleCheckIn}
        handleSetEmail={handleSetEmail}
        handleSetFullName={handleSetFullName}
        handleSetPhoneNumber={handleSetPhoneNumber}
        phoneNumber={phoneNumber}
      />

      {guests.length === 0 && (
        <p>There are no appointments scheduled at this time.</p>
      )}

      {guests.length > 0 && (
        <>
          <p>Appointments scheduled for today.</p>

          <div className='flex flex-col gap-3 overflow-scroll w-[300px]'>
            <GuestList guests={guests} />
          </div>
        </>
      )}
    </div>
  )
}

export default App
