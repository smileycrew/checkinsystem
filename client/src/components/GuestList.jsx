import GuestItem from "./GuestItem"

export default function GuestList({ guests }) {
  return (
    <>
      {guests.length === 0 && (
        <p>There are no appointments scheduled at this time.</p>
      )}

      {guests.length > 0 && (
        <div className="space-y-3">
          <p className="text-center">Appointments scheduled for today.</p>

          <ul className="flex flex-col gap-3 overflow-scroll w-[300px]">
            <li className="grid grid-cols-2">
              <p>No.</p>
              <p className="text-end">Guest</p>
            </li>
            {guests.map((guest, index) => (
              <GuestItem guest={guest} index={index + 1} key={guest.id} />
            ))}
          </ul>
        </div>
      )}
    </>
  )
}
