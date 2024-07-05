import Button from "./Button"
import Input from "./Input"

export default function Form({
  acceptEmail,
  email,
  fullName,
  isCheckingIn,
  handleAcceptEmail,
  handleCheckIn,
  handleSetEmail,
  handleSetFullName,
  handleSetPhoneNumber,
  phoneNumber,
}) {
  return (
    <form className="border flex flex-col gap-3 items-center p-3 rounded shadow-sm w-[500px]">
      <p>Online check in</p>

      <Input
        onChange={handleSetEmail}
        placeholder="Enter your email"
        type="email"
        value={email}
      />

      <Input
        onChange={handleSetFullName}
        placeholder="Enter your full name"
        value={fullName}
      />

      <Input
        onChange={handleSetPhoneNumber}
        placeholder="Enter your phone number"
        type="phoneNumber"
        value={phoneNumber}
      />

      {/* <div className="grid grid-cols-[1fr,4fr] items-center">
        <Input
          checked={acceptEmail}
          onChange={handleAcceptEmail}
          type="checkbox"
          value={acceptEmail}
        />

        <p>You will receive an email confirmation of your place in line.</p>
      </div> */}

      <div className="flex items-center justify-around w-full">
        <small>Powered by Edwin</small>

        <Button disabled={isCheckingIn} onClick={handleCheckIn}>
          Check in
        </Button>
      </div>
    </form>
  )
}
