import { useEffect, useState } from "react"
import Form from './components/Form'
import GuestList from './components/GuestList'

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
    <div className="flex md: max-h-screen min-h-screen w-screen">
      <aside className="border-r flex flex-col gap-5 px-10 py-5 max-h-screen min-h-screen overflow-scroll w-[400px]">
        <h1 className='text-2xl text-center'>Welcome to check in system!</h1>
        <p>This app is used to check in and save your spot to an imaginary company that does not exist! <span className='italic'>Think of checking in when getting a hair cut.</span></p>

        <GuestList guests={guests} />
      </aside>

      <div className="flex-1 flex items-center justify-center">
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
      </div>
    </div>
  )
}

export default App
