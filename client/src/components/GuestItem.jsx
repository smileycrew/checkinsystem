export default function GuestItem({ guest, index }) {
  const firstNameInitial = guest.fullName.split(" ")[0][0]
  const lastName = guest.fullName.split(" ")[1]

  return (
    <li className="border-b grid grid-cols-2">
      <p>{index}.</p>
      <p className="text-end">
        {firstNameInitial}. {lastName}
      </p>
    </li>
  )
}
